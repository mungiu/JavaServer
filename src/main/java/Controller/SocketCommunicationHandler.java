package Controller;

import Model.*;
import Utils.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class SocketCommunicationHandler implements Runnable {
    private Socket socket;
    private IPalletController iPalletController;
    private ILocationController iLocationController;
    private ICompanyController iCompanyController;
    private InputStream inputStream;
    private OutputStream outputStream;
    private SocketRequest request;
    private final String SUCCESS = "success";

    /**it instantiates the socket communication handler
     *
     * @param socket
     */
    SocketCommunicationHandler(Socket socket) {
        this.socket = socket;
        this.iCompanyController = new CompanyController(Database.getINSTANCE().getConnection());
        this.iLocationController = new LocationController(Database.getINSTANCE().getConnection());
        this.iPalletController = new PalletController(Database.getINSTANCE().getConnection());
    }

    /**
     * Initializes input and output stream for the socket communication and waits for the request.
     * When request arrives, identifies an action and perform necessary steps to create and send a response.
     * <p>
     * Action is defined in the ACTION field contained in the request. Each action has it's own separate
     * set of commands that will be executed in order to send a response back.
     */
    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String json = read();
            JSONObject jsonObject = new JSONObject(json);
            System.out.println(json);
            request = new SocketRequest(
                    jsonObject.getEnum(SocketRequest.ACTION.class, "Action"),
                    jsonObject.get("Obj").equals(null) ? null : jsonObject.getJSONObject("Obj"),
                    jsonObject.get("LocationID").equals(null) ? null : jsonObject.getString("LocationID"),
                    jsonObject.get("CompanyID").equals(null) ? null : jsonObject.getString("CompanyID"),
                    jsonObject.get("PalletID").equals(null) ? null : jsonObject.getString("PalletID")
            );
            System.out.println(request.getObj());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (request.getAction()) {
            case REGISTER_COMPANY:
                try {
                    Company company = new ObjectMapper().readValue(request.getObj().toString(), Company.class);
                    try {
                        iCompanyController.registerCompany(company);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    send(SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case ASSIGN_LOCATION_TO_COMPANY:
                try {
                    iLocationController.assignLocationToCompany(request.getLocationID(), request.getCompanyID(), Date.valueOf(LocalDate.now()));
                    send(SUCCESS);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case REMOVE_LOCATION_FROM_CURRENT_COMPANY:
                try {
                    iLocationController.removeLocationFromCurrentCompany(request.getLocationID(), request.getCompanyID());
                    send(SUCCESS);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case EDIT_COMPANY:
                try {
                    Company company = new ObjectMapper().readValue(request.getObj().toString(), Company.class);
                    iCompanyController.editCompany(company);
                    send(SUCCESS);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case GET_COMPANY_LIST:
                try {
                    CompanyList clientList = iCompanyController.getCompanyList();
                    System.out.println(clientList.toString());
                    String response = new ObjectMapper().writeValueAsString(clientList);
                    send(response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;

            case GET_COMPANY_BYID:
                try {
                    Company company = iCompanyController.getCompanyByID(request.getCompanyID());
                    String response = new ObjectMapper().writeValueAsString(company);
                    send(response);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case STORE_PALLET:
                try {
                    Pallet pallet = new ObjectMapper().readValue(request.getObj().toString(), Pallet.class);
                    iPalletController.StorePallet(pallet);
                    send(SUCCESS);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case REMOVE_PALLET:
                try {
                    iPalletController.removePallet(request.getPalletID(), request.getCompanyID());
                    send(SUCCESS);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case GET_PALLET_BYID:
                try {
                    Pallet pallet = iPalletController.getPalletByID(request.getPalletID(), request.getCompanyID());
                    String response = new ObjectMapper().writeValueAsString(pallet);
                    send(response);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case GET_PALLET_LIST:
                try {
                    PalletList palletList = iPalletController.getPalletList();
                    System.out.println(palletList.toString());
                    String response = new ObjectMapper().writeValueAsString(palletList);
                    send(response);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case GET_AVAILABLE_LOCATIONS:
                try {
                    LocationList locationList = iLocationController.getAvailableLocations();
                    System.out.println(locationList.toString());
                    String response = new ObjectMapper().writeValueAsString(locationList);
                    send(response);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case GET_LOCATIONS_OF_CURRENT_COMPANY:
                try {
                    LocationList locationList = iLocationController.getLocationsOfCurrentCompany(request.getCompanyID());
                    System.out.println(locationList.toString());
                    String response = new ObjectMapper().writeValueAsString(locationList);
                    send(response);

                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case EDIT_PALLET:
                try {
                    Pallet pallet = new ObjectMapper().readValue(request.getObj().toString(), Pallet.class);
                    try {
                        iPalletController.updatePallet(pallet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    send(SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * Reads the request from the sockets and transforms bytes into json
     */
    private String read() throws IOException {
        //translating input
        byte[] lenBytes = new byte[4];
        inputStream.read(lenBytes, 0, 4);
        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
        byte[] receivedBytes = new byte[len];
        inputStream.read(receivedBytes, 0, len);

        String json = new String(receivedBytes, 0, len);

        return json;
    }

    /**
     * Writes json response into bytes and sends it back to the receiver
     */
    private void send(String toSend) throws IOException {
        System.out.println(toSend);
        byte[] toSendBytes = toSend.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte) (toSendLen & 0xff);
        toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
        outputStream.write(toSendLenBytes);
        outputStream.write(toSendBytes);
    }
}
