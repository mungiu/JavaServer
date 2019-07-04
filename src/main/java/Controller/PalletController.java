package Controller;

import Model.Company;
import Model.Location;
import Model.Pallet;
import Model.PalletList;
import Utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PalletController implements IPalletController {
    private static String palletID = null;
	private static String DB_NAME;
    private Connection connection;

    public PalletController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }
    
    @Override
    public Pallet getPalletByID(String palletID, String companyID, String LocationID) {
    	Pallet pallet = new Pallet();  
    	
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");

        while (resultSet.next())
        {
            pallet.getPalletID().add(populatePallet(resultSet));
        }

        return pallet;
    }

    public Pallet populatePallet(ResultSet resultSet) throws SQLException {
        Pallet pallet = new Pallet();
        Location location = new Location();
    	Company com = new Company();
        pallet.setPalletID(resultSet.getString(1));
        com.setCompanyID(resultSet.getString(2));
        location.setLocationID(resultSet.getString(3));
        pallet.setPalletHeight(resultSet.getDouble(4));
        pallet.setPalletArea(resultSet.getDouble(5));
        pallet.setArrivalDate(resultSet.getDate(6));
        pallet.setDaysStored(resultSet.getInt(7));
        return  pallet;
    }
     

    @Override
    public void removePallet(String palletID, String companyID, String LocationID) {
       
    	Pallet pallet = new Pallet();  
    	Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("delete FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");

        while (resultSet.next())
        {
            pallet.getPalletID().add(populatePallet(resultSet));
        }

        return pallet;
    }
    
    public void populateRemovePallet(ResultSet resultSet) throws SQLException {
               resultSet.updateString(1, palletID);
    }


    @Override
    public void StorePallet(Pallet pallet, String companyID, String locationID) {

    }

    @Override
    public PalletList  getPalletList() {
    	PalletList palletList = new PalletList();
        Statement statement = connection.createStatement();
    	ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");
    				
    				while (resultSet.next())
    		        {
    		            palletList.add(populatePalletList(resultSet));
    		        }
    				return palletList;
    				
    					}
    public void populatePalletList(ResultSet resultSet) throws SQLException {
    
        ((PreparedStatement) resultSet).setString(1, palletID);
    			}
    
        }
