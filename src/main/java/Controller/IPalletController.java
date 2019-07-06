package Controller;

import Model.Pallet;
import Model.PalletList;

import java.sql.SQLException;

public interface IPalletController {
    Pallet getPalletByID(String palletID, String companyID) throws SQLException;
    void removePallet(String palletID, String companyID)throws SQLException;
    void StorePallet(Pallet pallet, String locationID, String companyID)throws SQLException;
    PalletList getPalletList() throws SQLException;
}
