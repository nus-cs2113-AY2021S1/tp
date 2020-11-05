package locationlist;

import location.Location;
import location.OutOfNuS;

import java.util.ArrayList;

public class LocationList {
    private final ArrayList<Location> locationList = new ArrayList<>();

    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * Flag to check if location exists.
     *
     * @param name name of the location to be checked
     * @return boolean value of if the location is found in the location list
     */
    public boolean checkValidLocation(String name) {
        for (Location location : locationList) {
            if (name.equalsIgnoreCase(location.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds location in the list based on the name input.
     *
     * @param name String that represents name of location to find
     * @return Location that is found in the list to be returned
     */
    public Location findLocation(String name) {
        Location locationReturned = null;
        int i = 0;
        for (Location location : locationList) {
            if (name.equalsIgnoreCase(location.getName())) {
                locationReturned = locationList.get(i);
                return locationReturned;
            }
            i++;
        }

        if (locationReturned == null) {
            locationReturned = new OutOfNuS(name);
            locationList.add(locationReturned);
        }
        return locationReturned;
    }
}
