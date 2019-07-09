package Controller;

import Model.Location;
import Model.LocationList;

import java.sql.SQLException;

public interface ILocationController {
    void assignLocationToCompany(String locationID, String companyID, String rentalStart, String rentalEnd) throws SQLException;
    void removeLocationFromCurrentCompany(String locationID, String companyID) throws SQLException;
    Location getLocationByID(String locationID) throws SQLException;
    LocationList getAvailableLocations() throws SQLException;
}
