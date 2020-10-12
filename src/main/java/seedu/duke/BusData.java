package seedu.duke;

import java.util.ArrayList;

public class BusData {

    private static ArrayList<Bus> buses = new ArrayList<>();

    public static void createBusList(ArrayList<Bus> busList) {
        buses.addAll(busList);
    }

    public static ArrayList<Bus> possibleBuses(String startingLoc, String destination) {
        ArrayList<Bus> busOptions = new ArrayList<>();
        for (Bus bus: buses) {
            ArrayList<BusStops> route = bus.getPossibleRoute(startingLoc, destination);
            if (route != null) {
                busOptions.add(new Bus(bus.busNumber, route));
            }
        }
        return busOptions;
    }

}
