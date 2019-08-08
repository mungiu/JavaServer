package Test;

import Controller.LocationController;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class AssignLocationToCompanyAndRemoveLocationFromCurrentCompanyTest {
    private LocationController locationController = new LocationController(Database.getINSTANCE().getConnection());

    @Test
    public void assignLocationToCompanyAndRemoveLocationFromCompanyTest() throws SQLException {
        locationController.removeLocationFromCurrentCompany("locE", "com1234");

        //test for assigning
        locationController.assignLocationToCompany("locE", "com1234", Date.valueOf("2019-01-01"));
        Assert.assertEquals("locE", locationController.getLocationsOfCurrentCompany("com1234").getLocations().get(1).getLocationID());
    }

}
