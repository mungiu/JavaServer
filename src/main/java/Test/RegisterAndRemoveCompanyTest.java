package Test;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import Controller.CompanyController;
import Model.Company;
import Utils.Database;

public class RegisterAndRemoveCompanyTest {

    private CompanyController cc = new CompanyController(Database.getConnection());
    @Test
    public void RegisterCompany() throws SQLException{
        Company c = new Company();
        c.setCompanyID("comIDTest");
        c.setEmail("comEmailtes");
        c.setName("comNametest");
        c.setPhone(6666666);
        cc.registerCompany(c);
//        PreparedStatement  statement = Database.getConnection().prepareStatement("insert into \"" + "WME" + "\".company (companyID, name, phone, email) "
//    					+ "values ('comIDTest', 'comNametest', 6666666, 'comEmailtes')");
//        
//    	statement.setString(1, c.getCompanyID());
//        statement.setString(2, c.getName());
//        statement.setInt(3, c.getPhone());
//        statement.setString(4, c.getEmail());
 //   	statement.executeUpdate();
    	
        
//        c.setCompanyID("regComID1");
//        c.setEmail("regComEmail");
//        c.setName("regComName");
//        c.setPhone(6666666);
        Assert.assertEquals("comIDTest",cc.getCompanyByID("comIDTest").getCompanyID());
        Assert.assertEquals("comEmailtes",cc.getCompanyByID("comIDTest").getEmail());
        Assert.assertEquals("comNametest",cc.getCompanyByID("comIDTest").getName());
        Assert.assertEquals(6666666,cc.getCompanyByID("comIDTest").getPhone());
        cc.removeCompany(c.getCompanyID());
  //      statement.close();
    }
   
}
