package Test;

import Controller.PalletController;
import Model.Pallet;
import Utils.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class getPalletListTest {
    private PalletController palletController = new PalletController(Database.getConnection());

    @Test
    public void getPalletList() throws SQLException {
        List<Pallet> actualList = palletController.getPalletList().getPallets();

        Assert.assertEquals("pal1111", actualList.get(0).getPalletID());
        Assert.assertEquals(1.1, actualList.get(0).getPalletHeight(), 0.0001);
        Assert.assertEquals(10.10, actualList.get(0).getPalletArea(), 0.0001);
        Assert.assertEquals(Date.valueOf("2019-01-10"), actualList.get(0).getArrivalDate());

        Assert.assertEquals("pal2222", actualList.get(1).getPalletID());
        Assert.assertEquals(2.2, actualList.get(1).getPalletHeight(), 0.0001);
        Assert.assertEquals(20.20, actualList.get(1).getPalletArea(), 0.0001);
        Assert.assertEquals(Date.valueOf("2019-02-10"), actualList.get(1).getArrivalDate());
    }
}
