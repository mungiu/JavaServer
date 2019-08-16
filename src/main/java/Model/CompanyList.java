package Model;

import java.util.ArrayList;

public class CompanyList {
    public ArrayList<Company> getCompanies() {
        return Companies;
    }

    private ArrayList<Company> Companies = new ArrayList<Company>();

    /** it returns the number of the companyList
     *
     * @return
     */
    public int size() {return Companies.size();}
}
