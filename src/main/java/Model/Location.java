package Model;

import java.sql.Date;

public class Location {
    private String locationID;

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public PalletList getPalletList() {
        return palletList;
    }

    private Date rentalStart;
    private Date rentalEnd;
    private PalletList palletList = new PalletList();
}
