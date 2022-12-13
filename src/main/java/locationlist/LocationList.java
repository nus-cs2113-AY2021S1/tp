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
            String input = name.toLowerCase().replaceAll("\\s","");
            if (input.equals(location.getName().toLowerCase().replaceAll("\\s",""))) {
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
        String input = name.toLowerCase().replaceAll("\\s","");
        for (Location location : locationList) {
            if (input.equalsIgnoreCase(location.getName().toLowerCase().replaceAll("\\s",""))) {
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

    /**
     * Method to check if string can be converted to an integer.
     *
     * @param input String to be converted
     * @return flag if string can be converted
     */
    public boolean checkIfInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
