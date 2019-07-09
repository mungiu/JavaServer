package Controller;

import Model.Pallet;
import Model.PalletList;
import Utils.Database;

import java.sql.*;

public class PalletController implements IPalletController {
    private static String DB_NAME;
    private Connection connection;
    private String schemaName;

    // it instantiates the pallet Controller with a private instance of the database and connection to the database.

    public PalletController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
        this.schemaName = "WME";
    }

    // it is used by other methods to populate the temporary pallet table in the database with the resulted pallets from these methods.

    public Pallet populatePallet(ResultSet resultSet) throws SQLException {
        Pallet pallet = new Pallet();
        pallet.setPalletID(resultSet.getString(1));
        pallet.setPalletHeight(resultSet.getDouble(2));
        pallet.setPalletArea(resultSet.getDouble(3));
        pallet.setArrivalDate(resultSet.getDate(4));
        pallet.setDaysStored(resultSet.getInt(5));
        return  pallet;
    }

    // it returns a specific pallet details when the pallet id and company id are requested.

    @Override
    public Pallet getPalletByID(String palletID, String companyID)throws SQLException {
        Pallet p = new Pallet();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".pallet where palletID = " + "'" + palletID + "' and companyid = '"+companyID+"'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while (resultSet.next())
        {
            p = populatePallet(resultSet);
        }

        return p;
    }

    // it removes the assigned pallet for a specific company

    @Override
    public void removePallet(String palletID, String companyID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".pallet where palletid ="+"'"+palletID+"'" + " and companyid = "+"'"+companyID+"'");
        statement.executeUpdate();
        statement.close();
    }

    // it assign a specific pallet for the renting company in a specific location.

    @Override
    public void StorePallet(Pallet pallet, String locationID, String companyID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".pallet (palletID, companyid, locationid, palletheight, palletarea, arrivaledate) values (?,?,?,?,?,?)");
        statement.setString(1, pallet.getPalletID());
        statement.setString(2, companyID);
        statement.setString(3, locationID);
        statement.setDouble(4,pallet.getPalletHeight());
        statement.setDouble(5,pallet.getPalletArea());
        statement.setDate(6,pallet.getArrivalDate());
        statement.executeUpdate();
        statement.close();
    }

    // it returns the list of pallets that are assigned in the application database.

    @Override
    public PalletList getPalletList() throws SQLException {

        PalletList palletList = new PalletList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".pallet");

        while (resultSet.next())
        {
            palletList.getPalletArrayList().add(populatePallet(resultSet));
        }

        return palletList;
    }
}
