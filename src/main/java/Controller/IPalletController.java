package Controller;

import Model.Pallet;
import Model.PalletList;

public interface IPalletController {
    Pallet getPalletByID(String palletID, String companyID, String LocationID);
    void removePallet(String palletID, String companyID, String LocationID);
    void StorePallet(Pallet pallet, String locationID);
    PalletList getPalletList();
}
