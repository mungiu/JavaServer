package Controller;

import Model.Company;
import Model.Location;
import Model.LocationList;

import java.sql.*;
import java.text.SimpleDateFormat;

public class LocationController implements ILocationController {

    private Connection connection;
    private String schemaName;
    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");


    // it instantiates the location controller with a private instance of the database and connection to database.

    public LocationController(Connection dbConnection) {
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    // it is used by other methods to populate the temporary location table in the database with the resulted locations from those methods.

    private Location populateLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        location.setLocationID(resultSet.getString(1));
        return location;
    }


    private Location populateRentedLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        Company company = new Company();
        company.setCompanyID(resultSet.getString(1));
        location.setLocationID(resultSet.getString(2));
        location.setRentalStart(resultSet.getDate(3));
        return location;

    }

    // it assigns a specific location for a specific company in the application database.
    @Override
    public void assignLocationToCompany(String locationID, String companyID, Date rentalStart) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".rentedlocation (companyID, locationid, rentalstart) values (?,?,?)");
        statement.setString(1, companyID);
        statement.setString(2, locationID);
        statement.setDate(3, rentalStart);
        statement.executeUpdate();
        statement.close();
    }

    // it removes an assigned location for a specific company.

    @Override
    public void removeLocationFromCurrentCompany(String locationID, String companyID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".rentedlocation where locationid = " + "'" + locationID + "'");
        statement.executeUpdate();
        statement.close();
    }

    // it returns the location details when a specific location id is requested.

    @Override
    public Location getLocationByID(String locationID) throws SQLException {
        Location location = new Location();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".locations where locationID = " + "'" + locationID + "'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while (resultSet.next()) {
            location = populateLocation(resultSet);
        }

        return location;
    }

    // it returns a list of available locations which are not rented yet.
    @Override
    public LocationList getAvailableLocations() throws SQLException {
        LocationList locationList = new LocationList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName +
                "\".availableLocations order by availablelocations asc");

        while (resultSet.next()) {
            locationList.getLocations().add(populateLocation(resultSet));
        }

        return locationList;
    }

    @Override
    public LocationList getLocationsOfCurrentCompany(String companyID) throws SQLException {
        LocationList locationList = new LocationList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName +
                "\".rentedlocation where companyid = '" + companyID + "' order by locationid asc");

        while (resultSet.next()) {
            locationList.getLocations().add(populateRentedLocation(resultSet));
        }
        return locationList;
    }
}
