package Controller;

import Model.*;
import Utils.Database;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;

public class LocationController implements ILocationController {

    private static String DB_NAME;
    private Connection connection;
    private String schemaName;

    // it instantiates the location controller with a private instance of the database and connection to database.

    public LocationController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
        this.schemaName = "WME";
    }

    // it is used by other methods to populate the temporary location table in the database with the resulted locations from those methods.

    public Location populateLocation(ResultSet resultSet) throws SQLException{
    Location location = new Location();
    location.setLocationID(resultSet.getString(1));
    return location;
}


public Location populateRentedLocation(ResultSet resultSet) throws SQLException{
    Location location  = new Location();
    Company company = new Company();
    company.setCompanyID(resultSet.getString(1));
    location.setLocationID(resultSet.getString(2));
    location.setRentalStart(resultSet.getDate(3));
    location.setRentalEnd(resultSet.getDate(4));
    return location;

}

// it assigns a specific location for a specific company in the application database.

    @Override
    public void assignLocationToCompany(String locationID, String companyID, Date rentalStart, Date rentalEnd) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".rentedlocation (companyID, locationid, rentalstart, rentalend) values (?,?,?,?)");
        statement.setString(1, companyID);
        statement.setString(2, locationID);
        statement.setDate(3, rentalStart);
        statement.setDate(4, rentalEnd);
        statement.executeUpdate();
        statement.close();
    }

    // it removes an assigned location for a specific company.

    @Override
    public void removeLocationFromCurrentCompany(String locationID,String companyID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".rentedlocation where locationid = "+"'"+locationID+"' and companyid = '"+companyID+"'");
        statement.executeUpdate();
        statement.close();
    }

    // it returns the location details when a specific location id is requested.

    @Override
    public Location getLocationByID(String locationID) throws SQLException{
        Location l = new Location();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".locations where locationID = " + "'" + locationID + "'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while (resultSet.next())
        {
            l = populateLocation(resultSet);
        }

        return l;
    }

    // it returns a list of available locations which are not rented yet.

    @Override
    public LocationList getAvailableLocations() throws SQLException{
        LocationList locationList = new LocationList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".available");

        while (resultSet.next())
        {
            locationList.getLocations().add(populateLocation(resultSet));
        }

        return locationList;
    }

    @Override
    public LocationList getLocationsOfCurrentCompany(String companyID) throws SQLException{
        LocationList locationList = new LocationList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".rentedlocation where companyid = '"+companyID+"'");

        while(resultSet.next()) {
            locationList.getLocations().add(populateRentedLocation(resultSet));
        }
        return locationList;
    }
}
