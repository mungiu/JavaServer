package Controller;

import Model.Company;
import Model.CompanyList;
import Utils.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CompanyController implements ICompanyController {
/*sdfsf*/
	
    private String DB_NAME;
    private Connection connection;

    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }

    
  //-------------------------------------------getCompanyID---------------------//
    
    public Company getCompanyByID(String companyID) {
    	
    	   Company company = new Company();  
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + DB_NAME + "\".company where CompanyID = ?");

           while (resultSet.next())
           {
               company.getCompanyID().add(populatCompanyID(resultSet));
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
        
    try {    
    	
    
        ResultSet resultSet = statement.executeQuery("insert into \"" + DB_NAME + "\".company (CompanyID, Name, Phone, Email) values (?,?,?,?)");
      
        resultSet.updateString(1, company.getCompanyID());
        resultSet.updateString(2, company.getName());
        resultSet.updateInt(3, company.getPhone());
        resultSet.updateString(4, company.getEmail());

        return;
    }
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
