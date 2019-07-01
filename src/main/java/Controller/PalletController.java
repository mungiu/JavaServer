package Controller;

import Model.Pallet;
import Model.PalletList;
import Utils.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PalletController implements IPalletController {
    private static String DB_NAME;
    private Connection connection;

    public PalletController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }

    public Pallet populatePallet(ResultSet resultSet) throws SQLException {
        Pallet pallet = new Pallet();
        pallet.setPalletID(resultSet.getString(1));
        pallet.setPalletHeight(resultSet.getDouble(2));
        pallet.setPalletArea(resultSet.getDouble(3));
        pallet.setArrivalDate(resultSet.getDate(4));
        pallet.setDaysStored(resultSet.getInt(5));
        return  pallet;
    }
    @Override
    public Pallet getPalletByID(String palletID, String companyID) {
        return null;
    }

    @Override
    public void removePallet(String palletID, String companyID) {

    }

    @Override
    public void StorePallet(Pallet pallet, String locationID) {

    }

    @Override
    public PalletList getPalletList() {
        return null;
    }
}
