package Model;

import java.sql.Date;

public class Location {
    private String locationID;
    private Date rentalStart;

    /**it returns the value of the private locationID variable.
     *
     * @return
     */
    public String getLocationID() {
        return locationID;
    }

    /**it edits the location id to the new one in the parameter.
     *
     * @param locationID
     */
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    /**it returns the value of the private rentalStart variable.
     *
     * @return
     */
    public Date getRentalStart() {
        return rentalStart;
    }

    /**it edits the rental start of a location to the new one in the parameter
     *
     * @param rentalStart
     */
    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }
}
