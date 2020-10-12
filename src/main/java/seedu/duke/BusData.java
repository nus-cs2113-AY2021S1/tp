package seedu.duke;

import java.util.ArrayList;

public class BusData {
    /*Constants*/
    private static final String ROUTE_AA1 = "aa1";
    private static final String ROUTE_AA2 = "aa2";

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

    public static Bus selectBus(String busCode) throws NullPointerException {
        for (Bus bus: buses) {
            if (bus.getBusNumber().equals(busCode.toUpperCase())) {
                return bus;
            }
        }
        return null;
    }
}
