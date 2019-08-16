package Model;

import java.util.ArrayList;

public class LocationList {
    public ArrayList<Location> getLocations() {
        return Locations;
    }

    private ArrayList<Location> Locations = new ArrayList<>();

    /**it returns the number of the locations list
     *
     * @return
     */
    public int size() {
        return Locations.size();
    }
}
