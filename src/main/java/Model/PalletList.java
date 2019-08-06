package Model;

import java.util.ArrayList;

public class PalletList {

    private ArrayList<Pallet> palletArrayList = new ArrayList<Pallet>();

    // it returns the value of the private palletArrayList variable

    public ArrayList<Pallet> getPalletArrayList() {
        return palletArrayList;
    }

    // it returns the size of the palletArrayList

    public  int size() {return palletArrayList.size();}
}
