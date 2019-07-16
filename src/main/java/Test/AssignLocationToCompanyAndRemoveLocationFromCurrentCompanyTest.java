package Test;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import Controller.LocationController;
import Utils.Database;

public class AssignLocationToCompanyAndRemoveLocationFromCurrentCompanyTest {
	LocationController lc = new LocationController(Database.getConnection());

	@Test
    public void assignLocationToCompanyAndRemoveLocationFromCompanyTest() throws SQLException{

        //test for assigning
        lc.assignLocationToCompany("locE","com1234",Date.valueOf("2019-01-01"), Date.valueOf("2019-11-30"));
        Assert.assertEquals("locE", lc.getLocationsOfCurrentCompany("com1234").getLocations().get(1).getLocationID());

        lc.removeLocationFromCurrentCompany("locE","com1234");
    }

}
