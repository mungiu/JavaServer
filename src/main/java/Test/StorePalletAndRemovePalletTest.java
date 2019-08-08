package Test;

import Controller.PalletController;
import Model.Pallet;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class StorePalletAndRemovePalletTest {
	  private PalletController palletController = new PalletController(Database.getINSTANCE().getConnection());
	    @Test
	    public void StorePaller() throws SQLException{
	       Pallet pallet = new Pallet();
	     	       
	        pallet.setPalletID("palStoreTest");
			pallet.setCompanyID("com1234");
			pallet.setLocationID("locA");
	        pallet.setPalletHeight(1.2);
	        pallet.setPalletArea(35.45);
	        pallet.setArrivalDate(Date.valueOf("2019-01-01"));
	        palletController.StorePallet(pallet);

	        Assert.assertEquals("palStoreTest", palletController.getPalletByID(pallet.getPalletID(), "com1234").getPalletID());
	        Assert.assertEquals(1.2, palletController.getPalletByID(pallet.getPalletID(), "com1234").getPalletHeight(),0.001);
	        Assert.assertEquals(35.45, palletController.getPalletByID(pallet.getPalletID(), "com1234").getPalletArea(),0.001);
	        Assert.assertEquals(pallet.getArrivalDate(), palletController.getPalletByID(pallet.getPalletID(), "com1234").getArrivalDate());
	        palletController.removePallet(pallet.getPalletID(), "com1234");
	    }
	   
	}
