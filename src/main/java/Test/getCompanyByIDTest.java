package Test;

import Controller.CompanyController;
import Model.Company;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;
import java.sql.SQLException;

public class getCompanyByIDTest {

    private CompanyController cc = new CompanyController(Database.getConnection());

 
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
    
}
