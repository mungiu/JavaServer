package Model;

import java.sql.Date;

public class Location {
	 private Date rentalStart;
	 private Date rentalEnd;
	 private PalletList palletList = new PalletList();
	    
    private String locationID;

    // it returns the value of the private locationID variable.

    public String getLocationID() {
        return locationID;
    }

    // it edits the location id to the new one in the parameter.

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    // it returns the value of the private rentalStart variable.

    public Date getRentalStart() {
        return rentalStart;
    }

    // it edits the rental start of a location to the new one in the parameter

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    // it returns the value of the private rental end variable.

    public Date getRentalEnd() {
        return rentalEnd;
    }

    // it edits the rental end of a location to the new one in the parameter.

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    // it returns the list of values for the rented pallets

    public PalletList getPalletList() {
        return palletList;
    }
}
