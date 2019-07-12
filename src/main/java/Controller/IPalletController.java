package Controller;

import java.sql.SQLException;

import Model.Company;
import Model.Location;
import Model.Pallet;
import Model.PalletList;

import java.sql.SQLException;

public interface IPalletController {

    Pallet getPalletByID(String palletID, String companyID) throws SQLException;
    void removePallet(String palletID, String companyID)throws SQLException;
    void StorePallet(Pallet pallet, String companyID, String locationID)throws SQLException;
    PalletList getPalletList() throws SQLException;
}
