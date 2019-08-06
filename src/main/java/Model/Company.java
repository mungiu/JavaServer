package Model;

public class Company {
    private String companyID;
    private String name;
    private int phone;
    private String email;
    private LocationList locationList = new LocationList();

    // it returns the value of the private companyID variable.

    public String getCompanyID() {
        return companyID;
    }

    // it edits the company id to the new one in the parameter.

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    // it returns the value of the private name variable.

    public String getName() {
        return name;
    }

    // it edits the company name to the new one in the parameter.

    public void setName(String name) {
        this.name = name;
    }

    // it returns the value of the private phone variable.

    public int getPhone() {
        return phone;
    }

    // it edits the company phone to the new one in the parameter.

    public void setPhone(int phone) {
        this.phone = phone;
    }

    // it returns the value of the private email variable.

    public String getEmail() {
        return email;
    }

    // it edits the company email to the new one in the parameter

    public void setEmail(String email) {
        this.email = email;
    }

    // it returns the value of the private LocationList variable

    public LocationList getLocationList() {
        return locationList;
    }

    // it edits the LocationList to the new one in the parameter

    public void setLocationList(LocationList locationList) {
        this.locationList = locationList;
    }

//    public LocationList getLocationList() {
//        return locationList;
//    }
//
//    private LocationList locationList = new LocationList();
}
