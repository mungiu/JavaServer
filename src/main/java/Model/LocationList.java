package Model;

import java.util.ArrayList;

public class LocationList {

    private ArrayList<Location> Locations = new ArrayList<Location>();

    // it returns the value of the private Locations variable

    public ArrayList<Location> getLocations() {
        return Locations;
    }

    // it returns the size of the Location List

    public int size() {return Locations.size();}
}
