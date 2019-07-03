package Controller;

import Model.Company;
import Model.CompanyList;

import java.sql.SQLException;

public interface ICompanyController {
    Company getCompanyByID(String companyID);
    void registerCompany(Company company) throws SQLException;
    CompanyList getCompanyList() throws SQLException;
    void editCompany(Company company);
}
