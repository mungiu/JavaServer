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
    private static final String DB_NAME = "WME";
    private static String dbAddress = "localhost";
    private static String dbUsername = "postgres";
    private static String dbPassword = "Jwan0090j";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":"+dbPort+"/" + DB_NAME;

    private CompanyController cc;
    private LocationController lc;
    private PalletController pc;
    private Location l;
    private LocationList all;
    private LocationList rll;
    private Company c;
    private CompanyList cl;
    private Pallet p;
    private PalletList pl;

    @Before
    public void setUp() throws Exception {
        l = new Location();
        l.setLocationID("loc1234");
        l.setRentalStart(Date.valueOf("2019-01-01"));
        l.setRentalEnd(Date.valueOf("2019-11-30"));
        c = new Company();
        c.setCompanyID("com1234");
        c.setEmail("comA.com");
        c.setName("comAAA");
        c.setPhone(1111111);
        p = new Pallet();
        p.setPalletID("pal123");
        all = new LocationList();
        rll = new LocationList();
        cl = new CompanyList();

        pl = new PalletList();
        cc = new CompanyController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        lc = new LocationController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));
        pc = new PalletController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword))
    }

    @Test
    public void RegisterCompanyTest() throws SQLException{
        cc.registerCompany(c);
        Assert.assertEquals(c.getCompanyID(),cc.getCompanyByID("com1234").getCompanyID());
    }

    @Test
    public void GetCompanyByIDTest() throws SQLException {
        Assert.assertEquals(c.getName(),cc.getCompanyByID("com1234").getName());
        Assert.assertEquals(c.getEmail(),cc.getCompanyByID("com1234").getEmail());
        Assert.assertEquals(c.getCompanyID(),cc.getCompanyByID("com1234").getCompanyID());
        Assert.assertEquals(c.getPhone(),cc.getCompanyByID("com1234").getPhone());
    }


    @Test
    public void getCompanyListTest() throws SQLException{
       Assert.assertEquals(cl.size(), cc.getCompanyList().size());
        Company c1 = new Company();
        c.setCompanyID("com2345");
        c.setEmail("test");
        c.setName("test");
        c.setPhone(0000000);
        cc.registerCompany(c1);
        Assert.assertEquals(cl.size(),cc.getCompanyList().size());
    }

    @Test
    public void getLocationsByIdTest() throws SQLException {
        Assert.assertEquals(l.getLocationID(), lc.getLocationByID("loc1234").getLocationID());
        Assert.assertEquals(l.getRentalStart(), lc.getLocationByID("loc1234").getRentalStart());
        Assert.assertEquals(l.getRentalStart(), lc.getLocationByID("loc1234").getRentalEnd());
    }

    @Test
    public void assignLocationToCompanyTest() throws SQLException{
        lc.assignLocationToCompany("loc1234","3333",Date.valueOf("2019-01-01"), Date.valueOf("2019-11-30"));
        Assert.assertEquals(l.getLocationID(),lc.getLocationByID("loc222"));
    }

    @Test
    public void getLocationOfCurrentCompanyTest() throws SQLException {
      Assert.assertEquals(rll.size(), lc.getLocationsOfCurrentCompany("com1234").size());
      lc.assignLocationToCompany("loc3456","com1234", Date.valueOf("2019-04-01"), Date.valueOf("2019-12-30"));
      Assert.assertEquals(rll.size(), lc.getLocationsOfCurrentCompany("com1234").size());
    }
    // the size method is added to make the test
    @Test
    public void getAvailableLocationsTest() throws SQLException{
       Assert.assertEquals(all.size(), lc.getAvailableLocations().size());
       Location l1 = new Location();
       l1.setLocationID("loc2345");
       Assert.assertEquals(all.size(), lc.getAvailableLocations().size());
    }

    @Test
    public void getPalletByIDTest() throws SQLException {
       Assert.assertEquals(p.getPalletID(), pc.getPalletByID("pal123", "com1234", "loc1234").getPalletID());
    }

    @Test
    public void storedPalletTest() throws SQLException {
        pc.StorePallet(p, "com1234", "loc1234");
        Assert.assertEquals(p.getPalletID(), pc.getPalletByID("pal123","com1234", "loc1234").getPalletID());
    }

    @Test
    public void getPalletTest() throws SQLException{
        Assert.assertEquals(pl.size(), pc.getPalletList().size());
        Pallet p1 = new Pallet();
        p1.setPalletID("pal234");
        Assert.assertEquals(pl.size(), pc.getPalletList().size();
    }
}
