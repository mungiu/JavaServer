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
               company = populateCompany(resultSet);
           }

           return company;
       }


       // it is used by other methods inorder to populate the temporary company table in the database by the resulted companies from those methods

    private Company populateCompany(ResultSet resultSet) throws  SQLException
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


    }



    // this method returns a list of the registered companies in the application database

    public CompanyList getCompanyList() throws SQLException {
        CompanyList companyList = new CompanyList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".company order by companyid asc");

        while (resultSet.next())
        {
            companyList.getCompanies().add(populateCompany(resultSet));
        }

        return companyList;
    }

    // this method is used when editing company details in the application database
    
    public void editCompany(Company company) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
        		"update \"" + schemaName + "\".company set companyID = '"+company.getCompanyID() +"',name ='"+company.getName()+"',phone ='"+company.getPhone()
        		+"',Email ='"+company.getEmail ()+ "' where companyid = '"+company.getCompanyID()+"'");
//        statement.setString(1, company.getCompanyID());
//        statement.setString(2, company.getName());
//        statement.setInt(3, company.getPhone());
//        statement.setString(4, company.getEmail());
        statement.executeUpdate();
        statement.close();
    }

    // this method removes a company from the application database when a specific company id is requested

    public void removeCompany(String companyID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".company where companyid = '"+companyID+"'");
        statement.executeUpdate();
        statement.close();
    }
}
