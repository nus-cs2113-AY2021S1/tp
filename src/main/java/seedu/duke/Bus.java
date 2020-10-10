package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class Bus {

    String busNumber;
    ArrayList<String> route = new ArrayList<>();

    public Bus(String busNo, String[] stops) {
        busNumber = busNo;
        route.addAll(Arrays.asList(stops));
    }



}
