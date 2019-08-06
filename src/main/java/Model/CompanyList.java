package Model;

import java.util.ArrayList;

public class CompanyList {

    private ArrayList<Company> Companies = new ArrayList<Company>();

    // it returns the value of the private Companies variable

    public ArrayList<Company> getCompanies() {
        return Companies;
    }

    // it returns the size of the Companies list

    public int size() {return Companies.size();}
}
