package Test;

import Controller.PalletController;
import Model.Company;
import Model.Location;
import Model.Pallet;
import Utils.Database;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class getPalletByIDTest {

    private PalletController palletController = new PalletController(Database.getConnection());
    private Pallet pallet = new Pallet();
    private Company com = new Company();
    private Location loc = new Location();

    @Before
    public void init() {
        pallet.setPalletID("pal1111");
        com.setCompanyID("com1234");
        loc.setLocationID("locA");
        pallet.setPalletHeight(1.1);
        pallet.setPalletArea(10.10);
        pallet.setArrivalDate(Date.valueOf("2019-10-01"));

        try {
            palletController.StorePallet(pallet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPalletByID() throws SQLException {
        Assert.assertEquals("pal1111", palletController.getPalletByID(pallet.getPalletID(), com.getCompanyID()).getPalletID());
    }

    @After
    public void after() {
        try {
            palletController.removePallet(pallet.getPalletID(), com.getCompanyID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
