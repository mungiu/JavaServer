package Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import Controller.LocationController;
import Utils.Database;

public class getLocationOfCurrentCompanyTest {
	LocationController lc = new LocationController(Database.getConnection());

	@Test
    public void GetLocationOfCurrentCompanyTest() throws SQLException {
      Assert.assertEquals("locA", lc.getLocationsOfCurrentCompany("com1234").getLocations().get(0).getLocationID());
    }

}
