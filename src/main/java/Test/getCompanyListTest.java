package Test;

import Controller.CompanyController;
import Model.Company;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class getCompanyListTest {

    private CompanyController companyController = new CompanyController(Database.getINSTANCE().getConnection());

    @Test
    public void getCompanyList() throws SQLException {
        Company com1 = new Company();
        com1.setCompanyID("com1234");
        com1.setName("comAAA");
        com1.setPhone(1111111);
        com1.setEmail("comA.com");

        Company com2 = new Company();
        com2.setCompanyID("com2345");
        com2.setName("comBBB");
        com2.setPhone(2222222);
        com2.setEmail("comB.com");

        Company com3 = new Company();
        com3.setCompanyID("com3456");
        com3.setName("comCCC");
        com3.setPhone(3333333);
        com3.setEmail("comC.com");

        Company com4 = new Company();
        com4.setCompanyID("com4567");
        com4.setName("comDDD");
        com4.setPhone(4444444);
        com4.setEmail("comD.com");

        Company com5 = new Company();
        com5.setCompanyID("com5678");
        com5.setName("comEEE");
        com5.setPhone(5555555);
        com5.setEmail("comE.com");

        List<Company> actualList = Arrays.asList(com1, com2, com3, com4, com5);
        List<Company> expected = companyController.getCompanyList().getCompanies();
        Assert.assertEquals(expected.get(0).getCompanyID(), actualList.get(0).getCompanyID());
        Assert.assertEquals(expected.get(0).getName(), actualList.get(0).getName());
        Assert.assertEquals(expected.get(0).getPhone(), actualList.get(0).getPhone());
        Assert.assertEquals(expected.get(0).getEmail(), actualList.get(0).getEmail());

        Assert.assertEquals(expected.get(1).getCompanyID(), actualList.get(1).getCompanyID());
        Assert.assertEquals(expected.get(1).getName(), actualList.get(1).getName());
        Assert.assertEquals(expected.get(1).getPhone(), actualList.get(1).getPhone());
        Assert.assertEquals(expected.get(1).getEmail(), actualList.get(1).getEmail());

        Assert.assertEquals(expected.get(2).getCompanyID(), actualList.get(2).getCompanyID());
        Assert.assertEquals(expected.get(2).getName(), actualList.get(2).getName());
        Assert.assertEquals(expected.get(2).getPhone(), actualList.get(2).getPhone());
        Assert.assertEquals(expected.get(2).getEmail(), actualList.get(2).getEmail());

        Assert.assertEquals(expected.get(3).getCompanyID(), actualList.get(3).getCompanyID());
        Assert.assertEquals(expected.get(3).getName(), actualList.get(3).getName());
        Assert.assertEquals(expected.get(3).getPhone(), actualList.get(3).getPhone());
        Assert.assertEquals(expected.get(3).getEmail(), actualList.get(3).getEmail());

        Assert.assertEquals(expected.get(4).getCompanyID(), actualList.get(4).getCompanyID());
        Assert.assertEquals(expected.get(4).getName(), actualList.get(4).getName());
        Assert.assertEquals(expected.get(4).getPhone(), actualList.get(4).getPhone());
        Assert.assertEquals(expected.get(4).getEmail(), actualList.get(4).getEmail());

    }

}
