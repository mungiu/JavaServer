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


    /**
     * Method for initializing the location controller with a connection parameter
     * @param dbConnection
     */
    public LocationController(Connection dbConnection) {
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    /**
     * Method for populating locations only with location id
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Location populateLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        location.setLocationID(resultSet.getString(1));
        return location;
    }

    /**
     * Method for populating rented locations with company id, location id and rental start date
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Location populateRentedLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        Company company = new Company();
        company.setCompanyID(resultSet.getString(1));
        location.setLocationID(resultSet.getString(2));
        location.setRentalStart(resultSet.getDate(3));
        return location;

    }

    /**
     * Method for assigning a new location to a company
     * @param locationID
     * @param companyID
     * @param rentalStart
     * @throws SQLException
     */
    @Override
    public void assignLocationToCompany(String locationID, String companyID, Date rentalStart) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".rentedlocation (companyID, locationid, rentalstart) values (?,?,?)");
        statement.setString(1, companyID);
        statement.setString(2, locationID);
        statement.setDate(3, rentalStart);
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for removing a location from the current viewing company
     * @param locationID
     * @param companyID
     * @throws SQLException
     */
    @Override
    public void removeLocationFromCurrentCompany(String locationID, String companyID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".rentedlocation where locationid = " + "'" + locationID + "'");
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for getting a location by inputting location id
     * @param locationID
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for getting all locations that have not been rented by any companies yet
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for getting all locations from the current viewing company
     * @param companyID
     * @return
     * @throws SQLException
     */
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
