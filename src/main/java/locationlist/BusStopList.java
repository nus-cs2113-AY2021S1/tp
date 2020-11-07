package locationlist;

import location.BusStop;

import java.util.ArrayList;

public class BusStopList {
    private final ArrayList<BusStop> busStopList = new ArrayList<>();

    public ArrayList<BusStop> getBusStopList() {
        return busStopList;
    }

    /**
     * Flag to check if the bus stop exists.
     *
     * @param name name of bus stop to be checked
     * @return boolean value of if the bus stop exist in the list
     */
    public boolean checkExistence(String name) {
        for (BusStop busStop : busStopList) {
            if (name.equals(busStop.getName())) {
                return true;
            }
        }
        return false;
    }
}
