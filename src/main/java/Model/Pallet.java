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


    /**it returns the value of the private variable companyID
     *
     * @return
     */
    public String getCompanyID() {
        return companyID;
    }

    /**it edits the companyID with the new value
     *
     * @param companyID
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    /**it returns the value of the private variable locationID
     *
     * @return
     */
    public String getLocationID() {
        return locationID;
    }

    /**it edits the location value with the new value
     *
     * @param locationID
     */
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    /**it returns the value of the private palletID variable.
     *
     * @return
     */
    public String getPalletID() {
        return palletID;
    }

    /**it edits the pallet id  to the new one in the parameter.
     *
     * @param palletID
     */
    public void setPalletID(String palletID) {
        this.palletID = palletID;
    }

    /**it returns the value of the private palletHeight variable.
     *
     * @return
     */
    public double getPalletHeight() {
        return palletHeight;
    }

    /**it edits the pallet height to the new one in the parameter.
     *
     * @param palletHeight
     */
    public void setPalletHeight(double palletHeight) {
        this.palletHeight = palletHeight;
    }

    /**it returns the value of the private palletArea variable.
     *
     * @return
     */
    public double getPalletArea() {
        return palletArea;
    }

    /**it edits the pallet area to the new one in the parameter
     *
     * @param palletArea
     */
    public void setPalletArea(double palletArea) {
        this.palletArea = palletArea;
    }

    /**it returns the value of the private arrivalDate variable.
     *
     * @return
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**it edits the arrival date of the pallet to the new one in the parameter.
     *
     * @param arrivalDate
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**it returns the number of days in which the pallet is stored.
     *
     * @return
     */
    public String getDaysStored() {
        return daysStored;
    }

    /**it edits the number of the days in which the pallet is stored to the new one in the parameter.
     *
     * @param daysStored
     */
    public void setDaysStored(String daysStored) {
        this.daysStored = daysStored;
    }
}
