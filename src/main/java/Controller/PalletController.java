package Controller;

import Model.Pallet;
import Model.PalletList;

import java.sql.*;


public class PalletController implements IPalletController {
    private Connection connection;
    private String schemaName;

    // it instantiates the pallet Controller with a private instance of the database and connection to the database.
    public PalletController(Connection dbConnection) {
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    // it is used by other methods to populate the temporary pallet table in the database with the resulted pallets from these methods.
    private Pallet populatePallet(ResultSet resultSet) throws SQLException {
        Pallet pallet = new Pallet();
        pallet.setPalletID(resultSet.getString(1));
        pallet.setCompanyID(resultSet.getString(2));
        pallet.setLocationID(resultSet.getString(3));
        pallet.setPalletHeight(resultSet.getDouble(4));
        pallet.setPalletArea(resultSet.getDouble(5));
        pallet.setArrivalDate(resultSet.getDate(6));
        pallet.setDaysStored(resultSet.getString(7));

        return pallet;
    }

    // it returns a specific pallet details when the pallet id and company id are requested.
    @Override
    public Pallet getPalletByID(String palletID, String companyID) throws SQLException {
        Pallet p = new Pallet();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".palletstored where palletID = " + "'" + palletID + "' and companyid = '" + companyID + "'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while (resultSet.next()) {
            p = populatePallet(resultSet);
        }

        return p;

    }

    // it removes the assigned pallet for a specific company
    @Override
    public void removePallet(String palletID, String companyID) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName +
                "\".pallet where palletid = '" + palletID + "'" +
                " and companyid = '" + companyID + "'");
        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.commit();
        statement.close();
    }

    // it assign a specific pallet for the renting company in a specific location.
    @Override
    public void StorePallet(Pallet pallet) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".pallet (palletID, companyid, locationid, palletheight, palletarea, arrivaldate) values (?,?,?,?,?,?)");
        statement.setString(1, pallet.getPalletID());
        statement.setString(2, pallet.getCompanyID());
        statement.setString(3, pallet.getLocationID());
        statement.setDouble(4, pallet.getPalletHeight());
        statement.setDouble(5, pallet.getPalletArea());
        statement.setDate(6, pallet.getArrivalDate());
        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.commit();
        statement.close();
    }

    // it returns the list of pallets that are assigned in the application database.
    @Override
    public PalletList getPalletList() throws SQLException {

        PalletList palletList = new PalletList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".palletstored order by palletid asc");

        while (resultSet.next()) {
            palletList.getPallets().add(populatePallet(resultSet));
        }

        return palletList;
    }

    @Override
    public void updatePallet(Pallet pallet) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "update \"" + schemaName + "\".pallet set " +
                        "LocationID = '" + pallet.getLocationID() + "'" +
                        ", PalletHeight ='" + pallet.getPalletHeight() + "'" +
                        ", PalletArea ='" + pallet.getPalletArea() + "'" +
                        ", ArrivalDate ='" + pallet.getArrivalDate() + "'" +
                        " where palletID = '" + pallet.getPalletID() + "'" +
                        " and companyID = '" + pallet.getCompanyID() + "'");
        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        statement.close();
    }
}