package Model;

import java.util.ArrayList;

public class Company {

    private String companyID;
    private String Name;
    private int phone;
    private String email;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocationList getLocationList() {
        return locationList;
    }

    private LocationList locationList = new LocationList();
}
