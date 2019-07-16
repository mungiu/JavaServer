package Test;

import java.sql.Date;
import java.sql.SQLException;
import org.junit.Test;
import Model.Company;
import Model.Location;
import Model.Pallet;

public class getPalletByIDTest {
	@Test
    public void getPalletByID() throws SQLException {
        Pallet p = new Pallet();
        Company com = new Company();
        Location loc = new Location();
        p.setPalletID("pal1111");
        com.setCompanyID("com1234");
        loc.setLocationID("locA");
        p.setPalletHeight(1.1);
        p.setPalletArea(10.10);
        p.setArrivalDate(Date.valueOf("2019-10-01"));
              
                
    }

}
