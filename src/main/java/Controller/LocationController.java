package Controller;

import Model.Location;
import Model.LocationList;
import Utils.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationController implements ILocationController {

    private static String DB_NAME;
    private Connection connection;

    public LocationController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }

public Location populateLocation(ResultSet resultSet) throws SQLException{
    Location location = new Location();
    location.setLocationID(resultSet.getString(1));
    location.setRentalStart(resultSet.getDate(2));
    location.setRentalEnd(resultSet.getDate(3));

    return location;
}
    @Override
    public void assignLocationToCompany(String locationID, String companyID) {

    }

    @Override
    public void removeLocationFromCurrentCompany(String locationID, String companyID) {

    }

    @Override
    public Location getLocationByID(String locationID) {
        return null;
    }

    @Override
    public LocationList getAvailableLocations() {
        return null;
    }
}
