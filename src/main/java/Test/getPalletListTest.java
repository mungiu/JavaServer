package Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import Controller.PalletController;
import Model.Company;
import Model.Location;
import Model.Pallet;
import Utils.Database;

public class getPalletListTest <p>{

	 private PalletController pc = new PalletController(Database.getConnection());
	  
	    @Test
	    public void getPalletList() throws SQLException{
	    	Pallet pal1 = new Pallet();
	    	Company c1 = new Company();
	    	Location loc1 = new Location();
	    	pal1.setPalletID("pal1111");
	    	c1.setCompanyID("com1234");
	    	loc1.setLocationID("locA");
	    	pal1.setPalletHeight(1.1);
	    	pal1.setPalletArea(10.10);
	    	pal1.setArrivalDate(Date.valueOf("2019-01-10"));
	    	
	    	    	
	    	Pallet pal2 = new Pallet();
	    	Company c2 = new Company();
	    	Location loc2 = new Location();
	    	pal2.setPalletID("pal2222");
	    	c2.setCompanyID("com2345");
	    	loc2.setLocationID("locB");
	    	pal2.setPalletHeight(2.2);
	    	pal2.setPalletArea(20.20);
	    	pal2.setArrivalDate(Date.valueOf("2019-02-10"));
	    	
	    	Pallet pal3 = new Pallet();
	    	Company c3 = new Company();
	    	Location loc3 = new Location();
	    	pal3.setPalletID("pal3333");
	    	c3.setCompanyID("com3456");
	    	loc3.setLocationID("locC");
	    	pal3.setPalletHeight(3.3);
	    	pal3.setPalletArea(30.30);
	    	pal3.setArrivalDate(Date.valueOf("2019-03-10"));
	    	
	    	Pallet pal4 = new Pallet();
	    	Company c4 = new Company();
	    	Location loc4 = new Location();
	    	pal4.setPalletID("pal4444");
	    	c4.setCompanyID("com4567");
	    	loc4.setLocationID("locD");
	    	pal4.setPalletHeight(4.4);
	    	pal4.setPalletArea(40.40);
	    	pal4.setArrivalDate(Date.valueOf("2019-04-10"));
	    	
	    	Pallet pal5 = new Pallet();
	    	Company c5 = new Company();
	    	Location loc5 = new Location();
	    	pal5.setPalletID("pal5555");
	    	c5.setCompanyID("com5678");
	    	loc5.setLocationID("locE");
	    	pal5.setPalletHeight(5.5);
	    	pal5.setPalletArea(50.50);
	    	pal5.setArrivalDate(Date.valueOf("2019-05-10"));
	    	
	    	List<Pallet> actualList = Arrays.asList(pal1, pal2, pal3, pal4, pal5);
	    	List<Pallet> expected = pc.getPalletList().getPalletArrayList();
	    	Assert.assertEquals(expected.get(0).getPalletID(), actualList.get(0).getPalletID());
	    	Assert.assertEquals(expected.get(0).getPalletHeight(), actualList.get(0).getPalletHeight(),0.0001);
	    	Assert.assertEquals(expected.get(0).getPalletArea(), actualList.get(0).getPalletArea(), 0.0001);
	    	Assert.assertEquals(expected.get(0).getArrivalDate(), actualList.get(0).getArrivalDate());
	    	
	    	Assert.assertEquals(expected.get(1).getPalletID(), actualList.get(1).getPalletID());
	    	Assert.assertEquals(expected.get(1).getPalletHeight(), actualList.get(1).getPalletHeight(),0.0001);
	    	Assert.assertEquals(expected.get(1).getPalletArea(), actualList.get(1).getPalletArea(), 0.0001);
	    	Assert.assertEquals(expected.get(1).getArrivalDate(), actualList.get(1).getArrivalDate());
	    	
	    	Assert.assertEquals(expected.get(2).getPalletID(), actualList.get(2).getPalletID());
	    	Assert.assertEquals(expected.get(2).getPalletHeight(), actualList.get(2).getPalletHeight(),0.0001);
	    	Assert.assertEquals(expected.get(2).getPalletArea(), actualList.get(2).getPalletArea(), 0.0001);
	    	Assert.assertEquals(expected.get(2).getArrivalDate(), actualList.get(2).getArrivalDate());
	    	
	    	Assert.assertEquals(expected.get(3).getPalletID(), actualList.get(3).getPalletID());
	    	Assert.assertEquals(expected.get(3).getPalletHeight(), actualList.get(3).getPalletHeight(),0.0001);
	    	Assert.assertEquals(expected.get(3).getPalletArea(), actualList.get(3).getPalletArea(), 0.0001);
	    	Assert.assertEquals(expected.get(3).getArrivalDate(), actualList.get(3).getArrivalDate());
	    	
	    	Assert.assertEquals(expected.get(4).getPalletID(), actualList.get(4).getPalletID());
	    	Assert.assertEquals(expected.get(4).getPalletHeight(), actualList.get(4).getPalletHeight(),0.0001);
	    	Assert.assertEquals(expected.get(4).getPalletArea(), actualList.get(4).getPalletArea(), 0.0001);
	    	Assert.assertEquals(expected.get(4).getArrivalDate(), actualList.get(4).getArrivalDate());
	    	
	    }

}
