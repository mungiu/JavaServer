package Model;

import java.util.ArrayList;

public class CompanyList {
    public ArrayList<Company> getCompanies() {
        return Companies;
    }

    private ArrayList<Company> Companies = new ArrayList<Company>();

    public int size() {return Companies.size();}
}
