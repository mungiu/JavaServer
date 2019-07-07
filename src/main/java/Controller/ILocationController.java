package Controller;

import Model.Location;
import Model.LocationList;

import java.sql.SQLException;

public interface ILocationController {
    void assignLocationToCompany(String locationID, String companyID) throws SQLException;
    void removeLocationFromCurrentCompany(String locationID) throws SQLException;
    Location getLocationByID(String locationID) throws SQLException;
    LocationList getAvailableLocations() throws SQLException;
}
