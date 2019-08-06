package Model;

import java.sql.Date;

public class Location {
    private String locationID;
    private Date rentalStart;

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

    // it edits the rentalStart of a location to the new one in the parameter

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

}
