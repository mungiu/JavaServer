package Test;

import org.junit.Assert;
import org.junit.Test;

import Controller.CompanyController;
import Model.Company;
import Utils.Database;

public class EditeCompanyTest {
    private CompanyController companyController = new CompanyController(Database.getINSTANCE().getConnection());

    @Test
    public void test() throws Exception {
        Assert.assertEquals(1111111, companyController.getCompanyByID("com1234").getPhone());

        Company c = new Company();
        c.setCompanyID("com1234");
        c.setName("comAAA");
        c.setPhone(1111112);
        c.setEmail("comA.com");
        companyController.editCompany(c);

        Assert.assertEquals("com1234", companyController.getCompanyByID("com1234").getCompanyID());
        Assert.assertEquals("comA.com", companyController.getCompanyByID("com1234").getEmail());
        Assert.assertEquals("comAAA", companyController.getCompanyByID("com1234").getName());
        Assert.assertEquals(1111112, companyController.getCompanyByID("com1234").getPhone());

        c.setPhone(1111111);
        companyController.editCompany(c);
    }
}
