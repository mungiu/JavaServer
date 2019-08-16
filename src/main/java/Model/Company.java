package Model;

public class Company {
    private String companyID;
    private String name;
    private int phone;
    private String email;
    private LocationList locationList = new LocationList();

    /**it returns the value of the private companyID variable.
     *
     * @return
     */
    public String getCompanyID() {
        return companyID;
    }

    /**it edits the company id to the new one in the parameter.
     *
     * @param companyID
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    /**it returns the value of the private name variable.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**it edits the company name to the new one in the parameter.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**it returns the value of the private phone variable.
     *
     * @return
     */
    public int getPhone() {
        return phone;
    }

    /**it edits the company phone to the new one in the parameter.
     *
     * @param phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**it returns the value of the private email variable.
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**it edits the company email to the new one in the parameter
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public LocationList getLocationList() {
        return locationList;
    }

    public void setLocationList(LocationList locationList) {
        this.locationList = locationList;
    }
}
