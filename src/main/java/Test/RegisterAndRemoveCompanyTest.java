package Test;

import Controller.CompanyController;
import Model.Company;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class RegisterAndRemoveCompanyTest {

    private CompanyController companyController = new CompanyController(Database.getINSTANCE().getConnection());

    @Test
    public void RegisterCompany() throws SQLException {
        Company c = new Company();
        c.setCompanyID("comIDTest");
        c.setEmail("comEmailtes");
        c.setName("comNametest");
        c.setPhone(6666666);
        companyController.registerCompany(c);

        Assert.assertEquals("comIDTest", companyController.getCompanyByID("comIDTest").getCompanyID());
        Assert.assertEquals("comEmailtes", companyController.getCompanyByID("comIDTest").getEmail());
        Assert.assertEquals("comNametest", companyController.getCompanyByID("comIDTest").getName());
        Assert.assertEquals(6666666, companyController.getCompanyByID("comIDTest").getPhone());
        companyController.removeCompany(c.getCompanyID());
    }

}
