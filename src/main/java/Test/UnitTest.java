package Test;

import Controller.CompanyController;
import Controller.LocationController;
import Controller.PalletController;
import Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UnitTest {
    private static int dbPort = 5433;
    private static final String DB_NAME = "postgres";
    private static String dbAddress = "localhost";
    private static String dbUsername = "postgres";
    private static String dbPassword = "1111";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":"+dbPort+"/" + DB_NAME;

    private CompanyController companyController;
    private LocationController locationController;
    private PalletController palletController;
    private Location location;
    private LocationList allLocationsList;
    private LocationList rentedLocationsList;
    private Company company = new Company();
    private CompanyList companyList;
    private Pallet pallet;
    private PalletList palletList;

    @Before
    public void setUp() throws Exception {
        company.setCompanyID("test");
        company.setEmail("test");
        company.setName("test");
        company.setPhone(1111);
        companyController = new CompanyController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        locationController = new LocationController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        palletController = new PalletController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
    }

    @Test
    public void RegisterCompanyTest() throws SQLException{
        companyController.registerCompany(company);
        Assert.assertEquals("test", companyController.getCompanyByID("test").getCompanyID());
        companyController.removeCompany("test");
    }

    @Test
    public void GetCompanyByIDTest() throws SQLException {
        Assert.assertEquals("comAAA", companyController.getCompanyByID("com1234").getName());
        Assert.assertEquals("comA.com", companyController.getCompanyByID("com1234").getEmail());
        Assert.assertEquals(1111111, companyController.getCompanyByID("com1234").getPhone());
    }


    @Test
    public void getCompanyListTest() throws SQLException{
        Assert.assertEquals(5, companyController.getCompanyList().size());
    }

    @Test
    public void getLocationsByIdTest() throws SQLException {
        Assert.assertEquals("locA", locationController.getLocationByID("locA").getLocationID());
    }

    @Test
    public void assignLocationToCompanyAndRemoveLocationFromCompanyTest() throws SQLException{

        //test for assigning
        locationController.assignLocationToCompany("locB","com1234",Date.valueOf("2019-01-01"), Date.valueOf("2019-11-30"));
        Assert.assertEquals(2, locationController.getLocationsOfCurrentCompany("com1234").size());

        //test for removing
        locationController.removeLocationFromCurrentCompany("locB","com1234");
        Assert.assertEquals(1,locationController.getLocationsOfCurrentCompany("com1234").size());
    }

    @Test
    public void getLocationOfCurrentCompanyTest() throws SQLException {
      Assert.assertEquals(1, locationController.getLocationsOfCurrentCompany("com1234").size());
      locationController.assignLocationToCompany("locB","com1234", Date.valueOf("2019-04-01"), Date.valueOf("2019-12-30"));
      Assert.assertEquals(2, locationController.getLocationsOfCurrentCompany("com1234").size());
      locationController.removeLocationFromCurrentCompany("locB","com1234");
    }

    // the size method is added to make the test
    @Test
    public void getAvailableLocationsTest() throws SQLException{
       Assert.assertEquals(1, locationController.getAvailableLocations().size());
       locationController.assignLocationToCompany("locB","com1234", Date.valueOf("2019-04-01"), Date.valueOf("2019-12-30"));
       Assert.assertEquals(0, locationController.getAvailableLocations().size());
       locationController.removeLocationFromCurrentCompany("locB","com1234");
    }

    @Test
    public void getPalletByIDTest() throws SQLException {
       Assert.assertEquals("2019-01-10", palletController.getPalletByID("pal1111", "com1234").getArrivalDate().toString());
    }

    @Test
    public void storedPalletTest() throws SQLException {
        Pallet p = new Pallet();
        p.setPalletID("test");
        p.setArrivalDate(Date.valueOf("2019-01-01"));
        p.setPalletArea(2.0);
        p.setPalletHeight(1.1);
        palletController.StorePallet(p, "com1234", "locA");
        Assert.assertEquals("test", palletController.getPalletByID("test","com1234").getPalletID());
        palletController.removePallet("test","com1234");
    }

    @Test
    public void getPalletTest() throws SQLException{
        Assert.assertEquals(4, palletController.getPalletList().size());
        Pallet p = new Pallet();
        p.setPalletID("test");
        p.setArrivalDate(Date.valueOf("2019-01-01"));
        p.setPalletArea(2.0);
        p.setPalletHeight(1.1);
        palletController.StorePallet(p,"com1234","locA");
        Assert.assertEquals(5, palletController.getPalletList().size());
        palletController.removePallet("test","com1234");
    }
}
