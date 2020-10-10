package seedu.duke;

import java.util.ArrayList;

public class BusData {

    private static ArrayList<Bus> buses = new ArrayList<>();

    public static void createBusList(ArrayList<Bus> busList) {
        buses.addAll(busList);
    }

}
