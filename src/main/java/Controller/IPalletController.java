package Controller;

import java.sql.SQLException;

import Model.Company;
import Model.Location;
import Model.Pallet;
import Model.PalletList;

public interface IPalletController {
    Pallet getPalletByID(String palletID) throws SQLException;
    void removePallet(String palletID) throws SQLException;
    void StorePallet(Pallet pallet, Company companyID, Location LocationID) throws SQLException;
    PalletList getPalletList() throws SQLException;
}
