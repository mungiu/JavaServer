package Controller;

import java.sql.SQLException;

import Model.Pallet;
import Model.PalletList;

public interface IPalletController {

    Pallet getPalletByID(String palletID, String companyID) throws SQLException;
    void removePallet(String palletID, String companyID)throws SQLException;
    void StorePallet(Pallet pallet, String companyID, String locationID)throws SQLException;
    PalletList getPalletList() throws SQLException;
}
