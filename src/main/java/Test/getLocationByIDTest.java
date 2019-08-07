package Test;

import Controller.LocationController;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class getLocationByIDTest {
    private LocationController locationController = new LocationController(Database.getConnection());

    @Test
    public void getLocationsByIdTest() throws SQLException {
        Assert.assertEquals("locA", locationController.getLocationByID("locA").getLocationID());
    }

}
