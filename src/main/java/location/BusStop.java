package location;

import java.util.Arrays;

/**
 * Represents bus stop objects.
 * Contains String name which is the name of the bus stop and
 * an array of Strings which is the list of buses available at the bus stop.
 */
public class BusStop {
    private final String name;
    private final String[] buses;

    public BusStop(String name, String[] buses) {
        this.name = name;
        this.buses = buses;
    }

    public String getName() {
        return name;
    }

    /**
     * Prepares string to be printed in a list.
     *
     * @return object to be printed in a certain format.
     */
    public String toString() {
        return name + "\nBuses Available: " + Arrays.toString(buses);
    }
}
