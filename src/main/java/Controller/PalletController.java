package Controller;

import Model.Company;
import Model.Location;
import Model.Pallet;
import Model.PalletList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PalletController implements IPalletController {
    private Connection connection;
    private String schemaName;

    // it instantiates the pallet Controller with a private instance of the database and connection to the database.

    public PalletController(Connection dbConnection){
        this.connection = dbConnection;
        this.schemaName = "WME";
    }
    


    // it is used by other methods to populate the temporary pallet table in the database with the resulted pallets from these methods.

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
<<<<<<< HEAD
        
=======

    // it returns a specific pallet details when the pallet id and company id are requested.
>>>>>>> master

    @Override
    public Pallet getPalletByID(String palletID, String companyID, String locationID)throws SQLException {
        Pallet p = new Pallet();
        Statement statement = connection.createStatement();
        String sqlStatement = "SELECT * FROM \"" + schemaName + "\".pallet where palletID = " + "'" + palletID + "' and companyid = '"+companyID+"'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while (resultSet.next())
        {
            p = populatePallet(resultSet);
        }

        return p;

    }

<<<<<<< HEAD
    public void removePallet(String palletID, String companyID, String locationID) throws SQLException{
=======
    // it removes the assigned pallet for a specific company

    @Override
    public void removePallet(String palletID, String companyID) throws SQLException{
>>>>>>> master
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".pallet where palletid ="+"'"+palletID+"'" + " and companyid = "+"'"+companyID+"'");
        statement.executeUpdate();
        statement.close();
    }

<<<<<<< HEAD
=======
    // it assign a specific pallet for the renting company in a specific location.
>>>>>>> master

    @Override
    public void StorePallet(Pallet pallet, String companyID , String locationID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".pallet (palletID, companyid, locationid, palletheight, palletarea, arrivaledate) values (?,?,?,?,?,?)");
        statement.setString(1, pallet.getPalletID());
        statement.setString(2, companyID);
        statement.setString(3, locationID);
        statement.setDouble(4,pallet.getPalletHeight());
        statement.setDouble(5,pallet.getPalletArea());
        statement.setDate(6,pallet.getArrivalDate());
        statement.executeUpdate();
        statement.close();
    }

<<<<<<< HEAD


=======
    // it returns the list of pallets that are assigned in the application database.

    @Override
>>>>>>> master
    public PalletList getPalletList() throws SQLException {

        PalletList palletList = new PalletList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".pallet");

        while (resultSet.next())
        {
            palletList.getPalletArrayList().add(populatePallet(resultSet));
        }

        return palletList;
    }
}


//public PalletList  getPalletList() throws SQLException {
//	Pallet pallet = new Pallet();
//	Company com = new Company();
//	Location loc = new Location();
//	PalletList palletList = new PalletList();
//  Statement statement = connection.createStatement();
//  ResultSet resultSet = statement.executeQuery("select * FROM \"" + DB_NAME + "\".Pallet;");
//				
//				while (resultSet.next())
//		        {
//		        pallet.setPalletID(resultSet.getString(1));
//		        com.setCompanyID(resultSet.getString(2));
//		        loc.setLocationID(resultSet.getString(3));
//		        pallet.setPalletHeight(resultSet.getDouble(4));
//		        pallet.setPalletArea(resultSet.getDouble(5));
//		        pallet.setArrivalDate(resultSet.getDate(6));
//		        
//		        }
//				return palletList;
//				
//					}
//public void populatePalletList(ResultSet resultSet) throws SQLException {
//
//  ((PreparedStatement) resultSet).setString(1, palletID);
//			}
//
//  }

//public void StorePallet(Pallet pallet, String locationID, String companyID) throws SQLException {
//Pallet pall = new Pallet();
//Company com = new Company();
//Statement statement= connection.createStatement();
//ResultSet resultSet = statement.executeQuery("insert into \"" + DB_NAME + "\".Pallet (PalletID, CompanyID, LocationID, PalletHeight, PalletArea, ArrivaleDate)"
//		                                                                + "  values  (?,?,?,?,?,?)");
//
//}


//@Override
//public void removePallet(String palletID, String companyID) throws SQLException {
//	Statement statement = connection.createStatement();
//  ResultSet resultSet = statement.executeQuery("delete FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");      
//          resultSet.updateString(1, palletID);
//}

//@Override
//public Pallet getPalletByID(String palletID) throws SQLException {
//	Pallet pallet = new Pallet();  
//	
//  Statement statement = connection.createStatement();
//  ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".Pallet where PalletID = '"+ palletID +"';");
//
//  while (resultSet.next())
//  {
//      pallet = populatePallet(resultSet);
//  }
//
//  return pallet;
//}

