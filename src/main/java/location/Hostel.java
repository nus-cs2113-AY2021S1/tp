package location;

import java.util.Arrays;

/**
 * Represents Hostel objects.
 * Contains an array of Strings nearestBusStops which represents the bus stops nearby.
 */
public class Hostel extends Location {
    String[] nearestBusStops;

    public Hostel(String name, String[] nearestBusStops) {
        super(name);
        this.type = LocationType.HOSTEL;
        this.nearestBusStops = nearestBusStops;
    }

    /**
     * Prepares string to be printed in a list.
     *
     * @return object to be printed in a certain format.
     */
    @Override
    public String toString() {
        return "(Hostel): " + name + "\nNearest bus stop(s): " + printNearestBusStops();
    }

    private String printNearestBusStops() {
        String str = Arrays.toString(nearestBusStops);
        str = str.substring(1, str.indexOf("]"));
        return str;
    }
}
