package locationlist;

import location.Location;

import java.util.ArrayList;

public class LocationList {
    private static final String filePath = "data/locations.txt";
    private final ArrayList<Location> locationList = new ArrayList<>();

    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * Flag to check if location exists
     *
     * @param name name of the location to be checked
     * @return boolean value of if the location is found in the location list
     */
    public boolean checkValidLocation(String name) {
        for (Location location : locationList) {
            if (name.equals(location.getName())) {
                return true;
            }
        }
        return false;
    }
}
