package Test;

import Controller.LocationController;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class AssignLocationToCompanyAndRemoveLocationFromCurrentCompanyTest {
	LocationController lc = new LocationController(Database.getConnection());

	@Test
    public void assignLocationToCompanyAndRemoveLocationFromCompanyTest() throws SQLException{
        lc.removeLocationFromCurrentCompany("locE","com1234");

        //test for assigning
        lc.assignLocationToCompany("locE","com1234",Date.valueOf("2019-01-01"));
        Assert.assertEquals("locE", lc.getLocationsOfCurrentCompany("com1234").getLocations().get(1).getLocationID());
    }

}
