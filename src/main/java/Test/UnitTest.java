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
    private static int dbPort = 5432;
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
    private Company company;
    private CompanyList companyList;
    private Pallet pallet;
    private PalletList palletList;

    @Before
    public void setUp() throws Exception {
        location = new Location();
        location.setLocationID("loc1234");
        location.setRentalStart(Date.valueOf("2019-01-01"));
        location.setRentalEnd(Date.valueOf("2019-11-30"));
        company = new Company();
        company.setCompanyID("com1234");
        company.setEmail("comA.com");
        company.setName("comAAA");
        company.setPhone(1111111);
        pallet = new Pallet();
        pallet.setPalletID("pal123");
        allLocationsList = new LocationList();
        rentedLocationsList = new LocationList();
        companyList = new CompanyList();

        palletList = new PalletList();
        companyController = new CompanyController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        locationController = new LocationController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        palletController = new PalletController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
    }

    @Test
    public void RegisterCompanyTest() throws SQLException{
        companyController.registerCompany(company);
        Assert.assertEquals(company.getCompanyID(), companyController.getCompanyByID("com1234").getCompanyID());
    }

    @Test
    public void GetCompanyByIDTest() throws SQLException {
        Company c = new Company();
        c.setCompanyID("com1234");
        c.setEmail("comA.com");
        c.setName("comAAA");
        c.setPhone(1111111);
        Assert.assertEquals(c.getName(), companyController.getCompanyByID("com1234").getName());
        Assert.assertEquals(c.getEmail(), companyController.getCompanyByID("com1234").getEmail());
        Assert.assertEquals(c.getCompanyID(), companyController.getCompanyByID("com1234").getCompanyID());
        Assert.assertEquals(c.getPhone(), companyController.getCompanyByID("com1234").getPhone());
    }


    @Test
    public void getCompanyListTest() throws SQLException{
       Assert.assertEquals(companyList.size(), companyController.getCompanyList().size());
        Company c1 = new Company();
        company.setCompanyID("com2345");
        company.setEmail("test");
        company.setName("test");
        company.setPhone(0000000);
        companyController.registerCompany(c1);
        Assert.assertEquals(companyList.size(), companyController.getCompanyList().size());
    }

//    @Test
//    public void getCompanyListTest() throws SQLException{
//        Company company = new Company();
//        company.setCompanyID("test");
//        company.setEmail("test");
//        company.setName("test");
//        company.setPhone(0000000);
//        companyController.registerCompany(company);
//        Assert.assertEquals(company.getCompanyID(),companyController.getCompanyByID("test").getCompanyID());
//    }

    @Test
    public void getLocationsByIdTest() throws SQLException {
        Assert.assertEquals(location.getLocationID(), locationController.getLocationByID("loc1234").getLocationID());
        Assert.assertEquals(location.getRentalStart(), locationController.getLocationByID("loc1234").getRentalStart());
        Assert.assertEquals(location.getRentalStart(), locationController.getLocationByID("loc1234").getRentalEnd());
    }

    @Test
    public void assignLocationToCompanyTest() throws SQLException{
        locationController.assignLocationToCompany("loc1234","3333",Date.valueOf("2019-01-01"), Date.valueOf("2019-11-30"));
        Assert.assertEquals(location.getLocationID(), locationController.getLocationByID("loc222"));
    }

    @Test
    public void getLocationOfCurrentCompanyTest() throws SQLException {
      Assert.assertEquals(rentedLocationsList.size(), locationController.getLocationsOfCurrentCompany("com1234").size());
      locationController.assignLocationToCompany("loc3456","com1234", Date.valueOf("2019-04-01"), Date.valueOf("2019-12-30"));
      Assert.assertEquals(rentedLocationsList.size(), locationController.getLocationsOfCurrentCompany("com1234").size());
    }
    // the size method is added to make the test
    @Test
    public void getAvailableLocationsTest() throws SQLException{
       Assert.assertEquals(allLocationsList.size(), locationController.getAvailableLocations().size());
       Location l1 = new Location();
       l1.setLocationID("loc2345");
       Assert.assertEquals(allLocationsList.size(), locationController.getAvailableLocations().size());
    }

    @Test
    public void getPalletByIDTest() throws SQLException {
       Assert.assertEquals(pallet.getPalletID(), palletController.getPalletByID("pal123", "com1234", "loc1234").getPalletID());
    }

    @Test
    public void storedPalletTest() throws SQLException {
        palletController.StorePallet(pallet, "com1234", "loc1234");
        Assert.assertEquals(pallet.getPalletID(), palletController.getPalletByID("pal123","com1234", "loc1234").getPalletID());
    }

    @Test
    public void getPalletTest() throws SQLException{
        Assert.assertEquals(palletList.size(), palletController.getPalletList().size());
        Pallet p1 = new Pallet();
        p1.setPalletID("pal234");
        Assert.assertEquals(palletList.size(), palletController.getPalletList().size());
    }
}
