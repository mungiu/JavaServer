package Test;

import Controller.CompanyController;
import Model.Company;
import org.junit.Assert;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UnitTest {
    private static int dbPort = 5432;
    private static final String DB_NAME = "WME";
    private static String dbAddress = "localhost";
    private static String dbUsername = "postgres";
    private static String dbPassword = "Jwan0090j";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":"+dbPort+"/" + DB_NAME;

    private CompanyController cc = new CompanyController(DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword));

    public UnitTest() throws SQLException {

    }


    @Test
    public void GetCompanyByIDTest() throws SQLException {
        Company c = new Company();
        c.setCompanyID("com1234");
        c.setEmail("comA.com");
        c.setName("comAAA");
        c.setPhone(1111111);
        Assert.assertEquals(c.getName(),cc.getCompanyByID("com1234").getName());
        Assert.assertEquals(c.getEmail(),cc.getCompanyByID("com1234").getEmail());
        Assert.assertEquals(c.getCompanyID(),cc.getCompanyByID("com1234").getCompanyID());
        Assert.assertEquals(c.getPhone(),cc.getCompanyByID("com1234").getPhone());
    }

    @Test
    public void RegisterCompanyTest() throws SQLException{
        Company c = new Company();
        c.setCompanyID("test");
        c.setEmail("test");
        c.setName("test");
        c.setPhone(0000000);
        cc.registerCompany(c);
        Assert.assertEquals(c.getCompanyID(),cc.getCompanyByID("test").getCompanyID());
    }
    
    @Test
    public void getCompanyListTest() throws SQLException{
        Company c = new Company();
        c.setCompanyID("test");
        c.setEmail("test");
        c.setName("test");
        c.setPhone(0000000);
        cc.registerCompany(c);
        Assert.assertEquals(c.getCompanyID(),cc.getCompanyByID("test").getCompanyID());
    }
    
    
}
