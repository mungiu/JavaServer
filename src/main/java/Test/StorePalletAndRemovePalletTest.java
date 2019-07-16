package Test;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import Controller.PalletController;
import Model.Pallet;
import Utils.Database;

public class StorePalletAndRemovePalletTest {
	  private PalletController pc = new PalletController(Database.getConnection());
	    @Test
	    public void StorePaller() throws SQLException{
	       Pallet p = new Pallet();
	     	       
	        p.setPalletID("palStoreTest");
	        p.setPalletHeight((double)1.2);
	        p.setPalletArea((double)35.45);
	       p.setArrivalDate(Date.valueOf("2019-01-01"));
	               
	        pc.StorePallet(p,"com1234", "locA");

	        Assert.assertEquals("palStoreTest",pc.getPalletByID(p.getPalletID(), "com1234").getPalletID());
	        Assert.assertEquals(1.2,pc.getPalletByID(p.getPalletID(), "com1234").getPalletHeight(),0.001);
	        Assert.assertEquals(35.45,pc.getPalletByID(p.getPalletID(), "com1234").getPalletArea(),0.001);
	        Assert.assertEquals(p.getArrivalDate(),pc.getPalletByID(p.getPalletID(), "com1234").getArrivalDate());
	        pc.removePallet(p.getPalletID(), "com1234");
	  //      statement.close();
	    }
	   
	}
