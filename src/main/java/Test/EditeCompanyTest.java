package Test;

import org.junit.Assert;
import org.junit.Test;

import Controller.CompanyController;
import Model.Company;
import Utils.Database;

public class EditeCompanyTest {
	CompanyController cc = new CompanyController(Database.getConnection());

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		Company c = new Company();
//		
//		c.setCompanyID("com1234");
//		c.setName("comAAA");
//		c.setPhone(1111111);
//		c.setEmail("comA.com");
//		
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		Company c = new Company();
//		c.setCompanyID("com1234");
//		c.setName("comAAA");
//		c.setPhone(1111112);
//		c.setEmail("comA.com");
//		
//		cc.editCompany(c);
//		
//		
//	}

	@Test
	public void test() throws Exception{
		Assert.assertEquals(1111111,cc.getCompanyByID("com1234").getPhone());
		
		Company c = new Company();
		c.setCompanyID("com1234");
		c.setName("comAAA");
		c.setPhone(1111112);
		c.setEmail("comA.com");	
		cc.editCompany(c);
		
		Assert.assertEquals("com1234",cc.getCompanyByID("com1234").getCompanyID());
        Assert.assertEquals("comA.com",cc.getCompanyByID("com1234").getEmail());
        Assert.assertEquals("comAAA",cc.getCompanyByID("com1234").getName());
        Assert.assertEquals(1111112,cc.getCompanyByID("com1234").getPhone());
        
        c.setPhone(1111111);
        cc.editCompany(c);
	}

}
