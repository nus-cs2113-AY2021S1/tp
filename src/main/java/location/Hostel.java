package location;

import java.util.Arrays;

public class Hostel extends Location{
    String[] nearestBusStops;

    public Hostel(String name, String[] nearestBusStops) {
        super(name);
        this.type = locationType.HOSTEL;
        this.nearestBusStops = nearestBusStops;
    }

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
