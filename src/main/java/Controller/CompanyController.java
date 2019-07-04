package Controller;

import Model.Company;
import Model.CompanyList;
import Utils.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyController implements ICompanyController {
/*sdfsf*/
	
    private String DB_NAME;
    private Connection connection;

    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }

    
  //-------------------------------------------getCompanyID---------------------//
    
    public Company getCompanyByID(String companyID) throws SQLException{
    	
    	   Company company = new Company();  
           Statement statement = connection.createStatement();
           //database statement ??
           String sqlStatement = "SELECT * FROM \"" + DB_NAME + "\".company where CompanyID = ?";
           ResultSet resultSet = statement.executeQuery(sqlStatement);

           while (resultSet.next())
           {
               String cid = resultSet.getString("companyid");
               String name = resultSet.getString("name");
               int phone = resultSet.getInt("phone");
               String email = resultSet.getString("email");
               company.setPhone(phone);
               company.setName(name);
               company.setEmail(email);
               company.setCompanyID(cid);
       //        company.getCompanyID().add(populatCompanyID(resultSet));
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
    	
    	Statement statement = connection.createStatement();

    	
    
        ResultSet resultSet = statement.executeQuery("insert into \"" + DB_NAME + "\".company (CompanyID, Name, Phone, Email) values (?,?,?,?)");
      
        resultSet.updateString(1, company.getCompanyID());
        resultSet.updateString(2, company.getName());
        resultSet.updateInt(3, company.getPhone());
        resultSet.updateString(4, company.getEmail());

    }
 

 
  //-------------------------------------------getCompanyList---------------------//
    public CompanyList getCompanyList() throws SQLException {
        CompanyList companyList = new CompanyList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".company");

        while (resultSet.next())
        {
            companyList.getCompanies().add(populatCompany(resultSet));
        }

        return companyList;
    }
    
    
    
    private Company populatCompany(ResultSet resultSet) throws  SQLException
    {
        Company company = new Company();

        company.setCompanyID(resultSet.getString(1));
        company.setName(resultSet.getString(2));
        company.setPhone(resultSet.getInt(3));
        company.setEmail((resultSet.getString(4)));

        return company;
    }

 //------------------------------------------editCompany---------------------//
    
    public void editCompany(Company company) {

    }
}
