package seedu.smarthomebot.data.location;

import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.commons.exceptions.InvalidLocationException;

import java.util.ArrayList;

/**
 * Represents the locations in SmartHomeBot.
 */
public class LocationList {

    private static ArrayList<String> locationList;

    public LocationList() {
        this.locationList = new ArrayList<>();
    }

    /**
     * Creating new location if is not existed.
     *
     * @param location used to be added into the location list.
     */
    public void addLocation(String location) throws DuplicateDataException {
        // create location from Appliance
        if (!isLocationCreated(location)) {
            this.locationList.add(location);
        } else {
            throw new DuplicateDataException();
        }
    }

    /**
     * Removing selected location with user input.
     *
     * @param location used to be removed from the location list.
     */
    public void removeLocation(String location) throws InvalidLocationException {
        if (!(isLocationCreated(location))) {
            throw new InvalidLocationException();
        } else {
            int removeIndex = getRemoveLocationIndex(location);
            this.locationList.remove(removeIndex);
        }
    }

    /**
     * Returns true if location string is not found.
     *
     * @param toCheckLocation used to identify the display index.
     * @return isValid true if location is not found in list.
     */
    public boolean isLocationCreated(String toCheckLocation) {
        boolean isValid = false;
        for (String location : this.locationList) {
            if (location.equals(toCheckLocation)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    /**
     * Returns list of Location stored SmartHomeBot.
     *
     * @return list of Location in SmartHomeBot in ArrayList String.
     */
    public ArrayList<String> getAllLocations() {
        return this.locationList;
    }

    /**
     * Returns true if location string is not found.
     *
     * @return list of Location in SmartHomeBot.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String location : this.locationList) {
            sb.append(location);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the index of selected Location to remove.
     *
     * @param toRemoveLocation name of Location to remove.
     * @return the index of selected location in integer, if integer is not found, method will return -1.
     */
    private int getRemoveLocationIndex(String toRemoveLocation) {
        int removeIndex = -1;
        int locationIndex = 0;
        for (String location : this.locationList) {
            if (location.equals(toRemoveLocation)) {
                removeIndex = locationIndex;
                break;
            } else {
                locationIndex++;
            }
        }
        return removeIndex;
    }


}
