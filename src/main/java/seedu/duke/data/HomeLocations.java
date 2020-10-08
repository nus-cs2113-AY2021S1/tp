package seedu.duke.data;

import seedu.duke.exceptions.InvalidAddtionOfLocation;

import java.util.ArrayList;

public class HomeLocations {

    private ArrayList<String> locations = new ArrayList<>();

    /**
     * Creating new location if is not existed.
     *
     * @param location used to be added into the location list
     */
    public void addLocation(String location) throws InvalidAddtionOfLocation {
        // create location from Appliance
        if (!isLocationCreated(location)) {
            this.locations.add(location);
        } else {
            // Will be replaced with throwing an exception
            throw new InvalidAddtionOfLocation();
        }
    }

    /**
     * Removing selected location with user input.
     *
     * @param location used to be removed from the location list
     */
    public void removeLocation(String location) {
        int removeIndex = -1;
        int locationIndex = 0;
        for (String l : locations) {
            if (l.equals(location)) {
                removeIndex = locationIndex;
                break;
            } else {
                locationIndex++;
            }
        }
        locations.remove(removeIndex);
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String l : locations) {
            sb.append(l);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if location string is not found.
     *
     * @param location used to identify the display index
     * @return isValid true if location is not found in list
     */
    public boolean isLocationCreated(String location) {
        boolean isValid = false;
        for (String l : locations) {
            if (l.equals(location)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
