package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class Bus {

    String busNumber;
    ArrayList<BusStops> route = new ArrayList<>();

    public Bus(String busNo, BusStops[] stops) {
        busNumber = busNo;
        route.addAll(Arrays.asList(stops));
    }

}
