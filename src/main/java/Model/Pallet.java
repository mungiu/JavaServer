package Model;

import java.sql.Date;

public class Pallet {

    private String palletID;
    private String companyID;
    private String locationID;
    private double palletHeight;
    private double palletArea;
    private Date arrivalDate;
    private String daysStored;


    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    // it returns the value of the private palletID variable.
    public String getPalletID() {
        return palletID;
    }

    // it edits the pallet id  to the new one in the parameter.
    public void setPalletID(String palletID) {
        this.palletID = palletID;
    }

    // it returns the value of the private palletHeight variable.
    public double getPalletHeight() {
        return palletHeight;
    }

    // it edits the pallet height to the new one in the parameter.
    public void setPalletHeight(double palletHeight) {
        this.palletHeight = palletHeight;
    }

    // it returns the value of the private palletArea variable.
    public double getPalletArea() {
        return palletArea;
    }

    // it edits the pallet area to the new one in the parameter
    public void setPalletArea(double palletArea) {
        this.palletArea = palletArea;
    }

    // it returns the value of the private arrivalDate variable.
    public Date getArrivalDate() {
        return arrivalDate;
    }

    // it edits the arrival date of the pallet to the new one in the parameter.
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    // it returns the number of days in which the pallet is stored.
    public String getDaysStored() {
        return daysStored;
    }

    // it edits the number of the days in which the pallet is stored to the new one in the parameter.
    public void setDaysStored(String daysStored) {
        this.daysStored = daysStored;
    }
}
