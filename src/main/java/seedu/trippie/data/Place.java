package seedu.trippie.data;

/**
 * Represents the individual place visited by the user.
 * Stores the name of the place, its day and the starting and ending time.
 */
public class Place {
    private final String name;
    private final int day;
    private final int start;
    private final int end;

    public Place(String name, int day, int start, int end) {
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the name of the place.
     *
     * @return String corresponding to the name of the place.
     */
    public String getPlaceName() {
        return name;
    }

    /**
     * Returns the day which the place is going to be visited.
     *
     * @return Integer of day.
     */
    public int getPlaceDay() {
        return day;
    }

    /**
     * Returns the starting time of the place visit.
     *
     * @return Integer of starting time.
     */
    public int getPlaceStartTime() {
        return start;
    }

    /**
     * Returns the ending time of the place visit.
     *
     * @return Integer of ending time.
     */
    public int getPlaceEndTime() {
        return end;
    }

    @Override
    public String toString() {
        return String.format("%04d",getPlaceStartTime()) + " - " + String.format("%04d", getPlaceEndTime())
                + " " + getPlaceName();
    }
}
