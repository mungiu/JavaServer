package Controller;

import Model.Pallet;
import Model.PalletList;

public interface IPalletController {
    Pallet getPalletByID(String palletID, String companyID);
    void removePallet(String palletID, String companyID);
    void StorePallet(Pallet pallet, String locationID);
    PalletList getPalletList();
}
