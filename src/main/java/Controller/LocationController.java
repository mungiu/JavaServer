package Controller;

import Model.Company;
import Model.Location;
import Model.LocationList;
import Utils.Database;

import java.sql.*;

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
    location.setRentalStart(resultSet.getDate(2));
    location.setRentalEnd(resultSet.getDate(3));

    return location;
}

// it assigns a specific location for a specific company in the application database.

    @Override
    public void assignLocationToCompany(String locationID, String companyID, Date rentalStart, Date rentalEnd) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".rentedlocation (companyID, locationid, rentalstart, rentalend) values (?,?,?,?)");
        Company company = new Company();
        statement.setString(1, companyID);
        statement.setString(2, locationID);
        statement.setDate(3, rentalStart);
        statement.setDate(4, rentalEnd);
        statement.executeUpdate();
        statement = connection.prepareStatement("delete from \"" + schemaName + "\".location where locationid = "+"'"+locationID+"'");
        statement.executeUpdate();
        statement.close();
    }

    // it removes an assigned location for a specific company.

    @Override
    public void removeLocationFromCurrentCompany(String locationID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".rentedlocation where locationid = "+"'"+locationID+"'");
        statement.executeUpdate();
        statement = connection.prepareStatement("insert into \"" + schemaName + "\".location (locationid) values (?)");
        statement.setString(1, locationID);
        statement.executeUpdate();
        statement.close();
    }

    // it returns the location details when a specific location id is requested.

    @Override
    public Location getLocationByID(String locationID) throws SQLException{
        Location l = new Location();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".location where locationID = " + "'" + locationID + "'";
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".location");

        while (resultSet.next())
        {
            locationList.getLocations().add(populateLocation(resultSet));
        }

        return locationList;
    }
}
