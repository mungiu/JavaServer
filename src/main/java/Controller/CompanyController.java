package Controller;

import Model.Company;
import Model.CompanyList;

import java.sql.*;

public class CompanyController implements ICompanyController {
	
    private String schemaName;
    private Connection connection;

    // it instantiates the company controller with a private instance from the database and connection to the database.

    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    
      // it returns a specific company details when a specific company id is requested
    
    public Company getCompanyByID(String companyID) throws SQLException{
    	
    	   Company company = new Company();  
           Statement statement = connection.createStatement();

           //database statement ??

           String sqlStatement = "SELECT * FROM \"" + schemaName + "\".company where CompanyID = " + "'" + companyID + "'";
           ResultSet resultSet = statement.executeQuery(sqlStatement);

           while (resultSet.next())
           {
               company = populatCompanyID(resultSet);
           }

           return company;
       }


       // it is used by other methods inorder to populate the temporary company table in the database by the resulted companies from those methods

    private Company populatCompanyID(ResultSet resultSet) throws  SQLException
    {
        Company company = new Company();

        company.setCompanyID(resultSet.getString(1));
        company.setName(resultSet.getString(2));
        company.setPhone(resultSet.getInt(3));
        company.setEmail((resultSet.getString(4)));

        return company;
    }
    
    
    // this method to register a new company in the application database

    public void registerCompany(Company company) throws SQLException {
    	

    	PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".company (companyID, name, phone, email) values (?,?,?,?)");
        statement.setString(1, company.getCompanyID());
        statement.setString(2, company.getName());
        statement.setInt(3, company.getPhone());
        statement.setString(4, company.getEmail());
    	statement.executeUpdate();
    	statement.close();
    
//        ResultSet resultSet = statement.executeQuery("insert into \"" + schemaName + "\".company (companyID, name, phone, email) values (?,?,?,?)");
      
//        resultSet.updateString(1, company.getCompanyID());
//        resultSet.updateString(2, company.getName());
//        resultSet.updateInt(3, company.getPhone());
//        resultSet.updateString(4, company.getEmail());


    }

<<<<<<< HEAD
 
  //-------------------------------------------getCompanyList---------------------//
    
=======

    // this method returns a list of the registered companies in the application database

>>>>>>> master
    public CompanyList getCompanyList() throws SQLException {
        CompanyList companyList = new CompanyList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".company");

        while (resultSet.next())
        {
            companyList.getCompanies().add(populatCompany(resultSet));
        }

        return companyList;
    }
    
    // what is the difference between this method and populate company id. need to be checked.........
    
    private Company populatCompany(ResultSet resultSet) throws  SQLException
    {
        Company tempCompany = new Company();

        tempCompany.setCompanyID(resultSet.getString(1));
        tempCompany.setName(resultSet.getString(2));
        tempCompany.setPhone(resultSet.getInt(3));
        tempCompany.setEmail((resultSet.getString(4)));

        return tempCompany;
    }

    // this method is used when editing company details in the application database
    
    public void editCompany(Company company) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update \"" + schemaName + "\".company set (companyID, name, phone, email) values (?,?,?,?)");
        statement.setString(1, company.getCompanyID());
        statement.setString(2, company.getName());
        statement.setInt(3, company.getPhone());
        statement.setString(4, company.getEmail());
        statement.executeUpdate();
        statement.close();
    }
}
