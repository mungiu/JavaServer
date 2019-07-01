package Controller;

import Model.Location;
import Model.LocationList;

public interface ILocationController {
    void assignLocationToCompany(String locationID, String companyID);
    void removeLocationFromCurrentCompany(String locationID, String companyID);
    Location getLocationByID(String locationID);
    LocationList getAvailableLocations();
}
