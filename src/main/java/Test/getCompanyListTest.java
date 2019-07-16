package Test;

import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;
import Controller.CompanyController;
import Model.Company;
import Utils.Database;

public class getCompanyListTest<c> {

    private CompanyController cc = new CompanyController(Database.getConnection());
  
    @Test
    public void getCompanyList() throws SQLException{
    	Company com1 = new Company();
    	com1.setCompanyID("com1111");
    	com1.setName("comAAA");
    	com1.setPhone(1111111);
    	com1.setEmail("comA.com");
    	
    	Company com2 = new Company();
    	com2.setCompanyID("com2222");
    	com2.setName("comBBB");
    	com2.setPhone(2222222);
    	com2.setEmail("comB.com");
    	
    	Company com3 = new Company();
    	com3.setCompanyID("com3333");
    	com3.setName("comCCC");
    	com3.setPhone(3333333);
    	com3.setEmail("comC.com");
    	
    	Company com4 = new Company();
    	com4.setCompanyID("com4444");
    	com4.setName("comDDD");
    	com4.setPhone(4444444);
    	com4.setEmail("comD.com");
    	
    	Company com5 = new Company();
    	com5.setCompanyID("com5555");
    	com5.setName("comEEE");
    	com5.setPhone(5555555);
    	com5.setEmail("comE.com");
    	
    	List<Company> actualList = Arrays.asList(com1, com2, com3, com4, com5);
    	List<Company> expected = cc.getCompanyList().getCompanies();
    	assertThat(actualList, is(expected));
    	
    }

	private Matcher<? super List<Company>> is(List<Company> expected) {
			return null;
	}

}
