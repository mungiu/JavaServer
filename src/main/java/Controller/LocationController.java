package Controller;

import Model.Location;
import Model.LocationList;
import Utils.Database;

import java.sql.*;
import java.util.Calendar;

public class LocationController implements ILocationController {

    private static String DB_NAME;
    private Connection connection;
    private String schemaName;

    public LocationController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
        this.schemaName = "WME";
    }

public Location populateLocation(ResultSet resultSet) throws SQLException{
    Location location = new Location();
    location.setLocationID(resultSet.getString(1));
    location.setRentalStart(resultSet.getDate(2));
    location.setRentalEnd(resultSet.getDate(3));

    return location;
}
    @Override
    public void assignLocationToCompany(String locationID, String companyID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".rentedlocation (companyID, locationid, rentalstart, rentalend) values (?,?,?,?)");
        statement.setString(1, companyID);
        statement.setString(2, locationID);
        statement.setInt(3, Calendar.DATE);
//        statement.setString(4, company.getEmail());

        statement.executeUpdate();
        statement = connection.prepareStatement("delete from \"" + schemaName + "\".location where locationid = "+"'"+locationID+"'");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void removeLocationFromCurrentCompany(String locationID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".rentedlocation where locationid = "+"'"+locationID+"'");
        statement.executeUpdate();
        statement = connection.prepareStatement("insert into \"" + schemaName + "\".location (locationid) values (?)");
        statement.setString(1, locationID);
        statement.executeUpdate();
        statement.close();
    }

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
