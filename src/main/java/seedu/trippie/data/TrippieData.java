package seedu.trippie.data;

import seedu.trippie.Storage;
import seedu.trippie.exception.TrippieException;

import java.util.ArrayList;
import java.util.List;

/**
 * TrippieData class stores all of the user's data during runtime.
 */
public class TrippieData {
    private Storage storage;
    private int defaultTripIndex;
    private List<Trip> tripList;
    private Trip currentTrip = null;

    /**
     * Updates the trip indices (index) attribute contained inside tripList to match their index in the List.
     */
    private void updateTripIndices() {
        for (Trip t: tripList) {
            t.setIndex(tripList.indexOf(t));
        }
    }

    /**
     * Creates a new TrippieData object with an empty tripList.
     * @param storage the storage object to be associated with this object.
     */
    public TrippieData(Storage storage) {
        this.tripList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Returns tripList.
     * @return tripList A list of trips that the user has.
     */
    public List<Trip> getTripList() {
        return tripList;
    }

    /**
     * Checks whether tripList is empty.
     * @return true if tripList is empty.
     */
    public boolean isTripListEmpty() {
        return tripList.isEmpty();
    }

    /**
     * Adds a trip to tripList.
     * @param trip a trip to be added. The index of inputted trip does not matter.
     */
    public void addTrip(Trip trip) {
        tripList.add(trip);
        updateTripIndices();
    }

    /**
     * Retrieves the trip object with a specified index.
     * @param index (0...N-1), where N is the number of trips inside tripList.
     * @return trip object associated with the index.
     */
    public Trip getTripFromIndex(int index) {
        return tripList.get(index);
    }

    /**
     * Removes the trip file associated with the specified trip name.
     * The trip file will be removed permanently.
     * The trip object is retained in tripList.
     *
     * @param name the trip name to be removed.
     */
    public void removeTripFile(String name) {
        storage.deleteTripFile(name);
    }

    /**
     * Removes the trip and its trip file with the specified trip index.
     * The trip file will be removed permanently.
     * The trip object is removed from tripList.
     *
     * @param index index of the trip
     * @return the removed trip
     */
    public Trip removeTripFromIndex(int index) {
        Trip t = tripList.remove(index);
        storage.deleteTripFile(t.getName());
        updateTripIndices();

        if (this.getTripListSize() > 0) {
            this.setCurrentTripFromIndex(this.getTripListSize() - 1);
        } else {
            this.setCurrentTripFromIndex(-1);
        }

        return t;
    }

    /**
     * Gets the size of tripList.
     *
     * @return N, number of trips inside tripList.
     */
    public int getTripListSize() {
        return tripList.size();
    }

    /**
     * Gets the current trip, checks the default trip if current trip has not been set yet.
     *
     * @return the current trip object.
     */
    public Trip getCurrentTrip() {
        if (this.currentTrip == null) {
            try {
                this.currentTrip = tripList.get(this.defaultTripIndex);
            } catch (IndexOutOfBoundsException | NullPointerException p) {
                return null;
            }
        }
        return this.currentTrip;
    }

    /**
     * Sets the tripList based on an external List of Trips.
     *
     * @param tripList the new tripList value to replace the curreent one.
     */
    public void setTripList(List<Trip> tripList) {
        this.tripList = new ArrayList<>(tripList);
    }

    /**
     * set the default trip index to a specified index.
     *
     * @param index The new default trip index.
     */
    public void setDefaultTripIndex(int index) {
        this.defaultTripIndex = index;
    }

    /**
     * Sets the current trip index to the specified trip index.
     * If index given is -1, then set current trip to null.
     *
     * @param index specified trip index to point to.
     */
    public void setCurrentTripFromIndex(int index) {
        if (index == -1) {
            this.currentTrip = null;
        } else {
            this.currentTrip = this.tripList.get(index);
            System.out.println("Current trip is set to '" + this.currentTrip.getName() + "'.");
        }
    }

    /**
     * Checks if an existing trip has exactly the same name with that of specified.
     *
     * @param name The name to be checked.
     * @return true if a trip exists with the specified name, else false.
     */
    public boolean doesTripNameExist(String name) {
        // Implemented with reference to
        // https://www.rgagnon.com/javadetails/java-check-if-a-filename-is-valid.html
        for (Trip trip: tripList) {
            if (trip.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads the currentTrip from storage files, provided it is not out of bounds.
     */
    public void loadCurrentTripFromFile() {
        if (currentTrip.getIndex() < this.getTripListSize()) {
            Trip tempTrip = storage.loadTripFromFile(currentTrip);
            assert tempTrip != null;
            currentTrip.setPlaceList(tempTrip.getPlaceListObject());
            currentTrip.setExpenseList(tempTrip.getExpenseListObject());
        }
    }

    /**
     * Lists all of the trips the user has.
     * @return A multiline string of all trips.
     */
    public String list() {
        String list = "";
        for (Trip trip : tripList) {
            assert trip != null;
            int maxDay = trip.getMaxDay();

            if (maxDay == 0) {
                list = list.concat(
                        (trip.getIndex() + 1) + ". " + trip.getName() + " " + "[No places or expenses added yet]" + "\n"
                );
            } else {
                boolean plural = maxDay != 1;
                String dayWord = plural ? "Days" : "Day";

                list = list.concat(
                        (trip.getIndex() + 1) + ". " + trip.getName() + " [" + trip.getMaxDay() + " " + dayWord + "]\n"
                );
            }
        }

        return list;
    }
}
