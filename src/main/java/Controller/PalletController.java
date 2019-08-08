package Controller;

import Model.Pallet;
import Model.PalletList;

import java.sql.*;


public class PalletController implements IPalletController {
    private Connection connection;
    private String schemaName;

    /**
     * Method for initializing pallet controller by inputting a connection
     * @param dbConnection
     */
    public PalletController(Connection dbConnection) {
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    /**
     * Method for populating retrieve pallet data so that it is ready to show for client side
     * @param resultSet
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for getting a pallet by inputting pallet id
     * @param palletID
     * @param companyID
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for removing a pallet from a company
     * @param palletID
     * @param companyID
     * @throws SQLException
     */
    @Override
    public void removePallet(String palletID, String companyID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName +
                "\".pallet where palletid = '" + palletID + "'" +
                " and companyid = '" + companyID + "'");
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for storing a pallet into the databse
     * @param pallet
     * @throws SQLException
     */
    @Override
    public void StorePallet(Pallet pallet) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".pallet (palletID, companyid, locationid, palletheight, palletarea, arrivaldate) values (?,?,?,?,?,?)");
        statement.setString(1, pallet.getPalletID());
        statement.setString(2, pallet.getCompanyID());
        statement.setString(3, pallet.getLocationID());
        statement.setDouble(4, pallet.getPalletHeight());
        statement.setDouble(5, pallet.getPalletArea());
        statement.setDate(6, pallet.getArrivalDate());
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for getting all pallets
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for updating a pallet's information
     * @param pallet
     * @throws SQLException
     */
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
        statement.executeUpdate();
        statement.close();
    }
}