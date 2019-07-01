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

    private static String DB_NAME;
    private Connection connection;

    public CompanyController(Connection dbConnection){
        this.connection = dbConnection;
        this.DB_NAME = Database.DB_NAME;
    }

    public Company getCompanyByID(String companyID) {
        return null;
    }

    public void registerCompany(Company company) {

    }

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

    public void editCompany(Company company) {

    }
}
