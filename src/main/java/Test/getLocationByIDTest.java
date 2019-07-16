package Test;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import Controller.LocationController;
import Utils.Database;

public class getLocationByIDTest {
	LocationController lc = new LocationController(Database.getConnection());

	@Test
    public void getLocationsByIdTest() throws SQLException {
        Assert.assertEquals("locA", lc.getLocationByID("locA").getLocationID());
    }

}
