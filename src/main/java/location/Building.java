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
        this.type = LocationType.BUILDING;
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

    /**
     * Convert the information about this Building to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString(){
        String busStops = null;
        for(String additionalInfo: nearestBusStops){
            busStops=additionalInfo + ",";
        }
        assert busStops != null;
        return "BLK/"+name+ "/" + busStops.substring(0,busStops.length()-1);
    }

    private String printNearestBusStops() {
        String str = Arrays.toString(nearestBusStops);
        str = str.substring(1, str.indexOf("]"));
        return str;
    }
}
