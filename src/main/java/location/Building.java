package location;

import java.util.Arrays;

/**
 * Represents Building objects.
 * Contains an array of Strings nearestBusStops which represents the bus stops nearby.
 */
public class Building extends Location {
    private final String[] nearestBusStops;

    public Building(String name, String[] nearestBusStops) {
        super(name);
        this.nearestBusStops = nearestBusStops;
        this.type = locationType.BUILDING;
    }

    /**
     * Prepares string to be printed in a list.
     *
     * @return object to be printed in a certain format.
     */
    @Override
    public String toString() {
        return "(Building): " + name + "\nNearest bus stop(s): " + printNearestBusStops();
    }

    private String printNearestBusStops() {
        String str = Arrays.toString(nearestBusStops);
        str = str.substring(1, str.indexOf("]"));
        return str;
    }
}
