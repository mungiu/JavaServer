package Model;

import java.util.ArrayList;

public class LocationList {
    public ArrayList<Location> getLocations() {
        return Locations;
    }

    private ArrayList<Location> Locations = new ArrayList<>();

    public int size() {
        return Locations.size();
    }
}
