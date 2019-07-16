package Test;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import Controller.LocationController;
import Utils.Database;

public class getAvailableLoationsTest {
	LocationController lc = new LocationController(Database.getConnection());

	  @Test
	    public void getAvailableLocationsTest() throws SQLException{
		  lc.removeLocationFromCurrentCompany("locA", "com1234");
		  // here we delete location A to make available first
		  
		  
	       Assert.assertEquals("locA", lc.getAvailableLocations().getLocations().get(0).getLocationID()); 
	       // here we compare that the location A is available
	       
	       
	       lc.assignLocationToCompany("locA", "com1234", Date.valueOf("2018-04-20"), Date.valueOf("2019-05-10")); 
	       // here we bring back the location A to the company 1234 to return the data to the same state
	      
	    }

}
