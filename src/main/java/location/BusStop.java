package location;

import java.util.Arrays;

public class BusStop {
    private String name;
    private String[] buses;

    public BusStop(String name, String[] buses) {
        this.name = name;
        this.buses = buses;
    }

    public String toString() {
        return name + "\nBuses Available: " + Arrays.toString(buses);
    }

    public String getName() {
        return name;
    }

    public String[] getBuses() {
        return buses;
    }
}
