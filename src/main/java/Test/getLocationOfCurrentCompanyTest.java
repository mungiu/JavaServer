package Test;

import Controller.LocationController;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class getLocationOfCurrentCompanyTest {
    private LocationController locationController = new LocationController(Database.getConnection());

    @Test
    public void GetLocationOfCurrentCompanyTest() throws SQLException {
        Assert.assertEquals("locA", locationController.getLocationsOfCurrentCompany("com1234").getLocations().get(0).getLocationID());
    }

}
