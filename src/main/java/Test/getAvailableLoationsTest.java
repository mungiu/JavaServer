package Test;

import Controller.LocationController;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class getAvailableLoationsTest {
    private LocationController locationController = new LocationController(Database.getINSTANCE().getConnection());

    @Test
    public void getAvailableLocationsTest() throws SQLException {
        // here we delete location A to make available first
        locationController.removeLocationFromCurrentCompany("locA", "com1234");


        // here we compare that the location A is available
        Assert.assertEquals("locA", locationController.getAvailableLocations().getLocations().get(0).getLocationID());


        // here we bring back the location A to the company 1234 to return the data to the same state
        locationController.assignLocationToCompany("locA", "com1234", Date.valueOf("2018-04-20"));
    }
}
