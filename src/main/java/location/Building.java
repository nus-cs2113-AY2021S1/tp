package location;

import java.util.Arrays;

public class Building extends Location{
    private String[] nearestBusStops;

    public Building(String name, String[] nearestBusStops) {
        super(name);
        this.nearestBusStops = nearestBusStops;
        this.type = locationType.BUILDING;
    }

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
