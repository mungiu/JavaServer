package Model;

import java.sql.Date;

public class Pallet {
    private String palletID;

    public String getPalletID() {
        return palletID;
    }

    public void setPalletID(String palletID) {
        this.palletID = palletID;
    }

    public double getPalletHeight() {
        return PalletHeight;
    }

    public void setPalletHeight(double palletHeight) {
        PalletHeight = palletHeight;
    }

    public double getPalletArea() {
        return PalletArea;
    }

    public void setPalletArea(double palletArea) {
        PalletArea = palletArea;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public int getDaysStored() {
        return daysStored;
    }

    public void setDaysStored(int daysStored) {
        this.daysStored = daysStored;
    }

    private double PalletHeight;
    private double PalletArea;
    private Date ArrivalDate;
    private int daysStored;
}
