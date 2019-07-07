package Controller;

import Model.Company;
import Model.CompanyList;

import java.sql.*;

public class CompanyController implements ICompanyController {
/*sdfsf*/
	
    private String schemaName;
    private Connection connection;

    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.schemaName = "WME";
    }

    
  //-------------------------------------------getCompanyID---------------------//
    
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
    private Company populatCompanyID(ResultSet resultSet) throws  SQLException
    {
        Company company = new Company();

        company.setCompanyID(resultSet.getString(1));
        company.setName(resultSet.getString(2));
        company.setPhone(resultSet.getInt(3));
        company.setEmail((resultSet.getString(4)));

        return company;
    }
    
    

//-------------------------------------------Register a Company---------------------//
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
 

 
  //-------------------------------------------getCompanyList---------------------//
    
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
    
    
    
    private Company populatCompany(ResultSet resultSet) throws  SQLException
    {
        Company tempCompany = new Company();

        tempCompany.setCompanyID(resultSet.getString(1));
        tempCompany.setName(resultSet.getString(2));
        tempCompany.setPhone(resultSet.getInt(3));
        tempCompany.setEmail((resultSet.getString(4)));

        return tempCompany;
    }

 //------------------------------------------editCompany---------------------//
    
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
