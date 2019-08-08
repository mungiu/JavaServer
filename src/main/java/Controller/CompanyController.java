package Controller;

import Model.Company;
import Model.CompanyList;

import java.sql.*;

public class CompanyController implements ICompanyController {
	
    private String schemaName;
    private Connection connection;

    /**
     * Method for initializing company controller with a connection parameter
     * @param dbConnection
     */
    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.schemaName = "WME";
    }


    /**
     * Method for getting a company by inputting its company id
     * @param companyID
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for populating retrieved companies so that it will be ready to show for clients
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Company populateCompany(ResultSet resultSet) throws  SQLException
    {
        Company company = new Company();

        company.setCompanyID(resultSet.getString(1));
        company.setName(resultSet.getString(2));
        company.setPhone(resultSet.getInt(3));
        company.setEmail((resultSet.getString(4)));

        return company;
    }


    /**
     * Method for registering a company to the database
     * @param company
     * @throws SQLException
     */
    public void registerCompany(Company company) throws SQLException {
    	

    	PreparedStatement statement = connection.prepareStatement("insert into \"" + schemaName + "\".company (companyID, name, phone, email) values (?,?,?,?)");
        statement.setString(1, company.getCompanyID());
        statement.setString(2, company.getName());
        statement.setInt(3, company.getPhone());
        statement.setString(4, company.getEmail());
    	statement.executeUpdate();
    	statement.close();


    }


    /**
     * Method for getting all companies from the database
     * @return
     * @throws SQLException
     */
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

    /**
     * Method for managing company details
     * @param company
     * @throws SQLException
     */
    public void editCompany(Company company) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
        		"update \"" + schemaName + "\".company set companyID = '"+company.getCompanyID() +"',name ='"+company.getName()+"',phone ='"+company.getPhone()
        		+"',Email ='"+company.getEmail ()+ "' where companyid = '"+company.getCompanyID()+"'");
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for removing a company from the database
     * @param companyID
     * @throws SQLException
     */
    public void removeCompany(String companyID) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from \"" + schemaName + "\".company where companyid = '"+companyID+"'");
        statement.executeUpdate();
        statement.close();
    }
}
