package Controller;

import Model.Company;
import Model.CompanyList;

import java.sql.SQLException;

public interface ICompanyController {
    Company getCompanyByID(String companyID) throws SQLException;
    void registerCompany(Company company) throws SQLException;
    CompanyList getCompanyList() throws SQLException;
    void editCompany(Company company) throws SQLException;
    void removeCompany(String companyID) throws SQLException;
}
