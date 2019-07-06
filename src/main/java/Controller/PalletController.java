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
    public Pallet getPalletByID(String palletID) throws SQLException {
    	Pallet pallet = new Pallet();  
    	
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");

        while (resultSet.next())
        {
            pallet = populatePallet(resultSet);
        }

        return pallet;
    }

    public Pallet populatePallet(ResultSet resultSet) throws SQLException {
        Pallet pallet = new Pallet();
        Company com = new Company();
        Location loc = new Location();
        pallet.setPalletID(resultSet.getString(1));
        com.setCompanyID(resultSet.getString(2));
        loc.setLocationID(resultSet.getString(3));
        pallet.setPalletHeight(resultSet.getDouble(4));
        pallet.setPalletArea(resultSet.getDouble(5));
        pallet.setArrivalDate(resultSet.getDate(6));
        pallet.setDaysStored(resultSet.getInt(7));
        return  pallet;
    }
     

    @Override
    public void removePallet(String palletID) throws SQLException {
    	Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("delete FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");      
                resultSet.updateString(1, palletID);
                
                
    }


    @Override
    public void StorePallet(Pallet pallet, Company companyID, Location location) throws SQLException {
    	Pallet pall = new Pallet();
    	Company com = new Company();
    	Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("insert into \"" + DB_NAME + "\".Pallet (PalletID, CompanyID, LocationID, PalletHeight, PalletArea, ArrivaleDate)"
        		                                                                + "  values  (?,?,?,?,?,?)");
        
    

    }

    @Override
    public PalletList  getPalletList() throws SQLException {
    	Pallet pallet = new Pallet();
    	Company com = new Company();
    	Location loc = new Location();
    	PalletList palletList = new PalletList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * FROM \"" + DB_NAME + "\".Pallet;");
    				
    				while (resultSet.next())
    		        {
    		        pallet.setPalletID(resultSet.getString(1));
    		        com.setCompanyID(resultSet.getString(2));
    		        loc.setLocationID(resultSet.getString(3));
    		        pallet.setPalletHeight(resultSet.getDouble(4));
    		        pallet.setPalletArea(resultSet.getDouble(5));
    		        pallet.setArrivalDate(resultSet.getDate(6));
    		        
    		        }
    				return palletList;
    				
    					}
    public void populatePalletList(ResultSet resultSet) throws SQLException {
    
        ((PreparedStatement) resultSet).setString(1, palletID);
    			}
    
        }
