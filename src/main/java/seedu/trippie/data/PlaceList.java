package seedu.trippie.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of places stored in a array-list.
 */
public class PlaceList {
    private List<Place> placeList;

    public PlaceList() {
        this.placeList = new ArrayList<>();
    }

    public PlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    /**
     * Returns List of Place object stored in list.
     *
     * @return List of Place.
     */
    public List<Place> getPlaceList() {
        return placeList;
    }

    /**
     * Sets list to the List of Place object passed.
     *
     * @param placeList the current List of Place.
     */
    public void setPlaceList(List<Place> placeList) {
        this.placeList = new ArrayList<>(placeList);
    }

    /**
     * Returns the maximum day of the place list.
     *
     * @return Integer of maximum day.
     */
    public int getMaxDay() {
        if (placeList.size() == 0) {
            return 0;
        }
        int maxDay = 0;
        for (Place place: placeList) {
            if (place.getPlaceDay() > maxDay) {
                maxDay = place.getPlaceDay();
            }
        }
        return maxDay;
    }
}
