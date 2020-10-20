package seedu.duke;

import java.util.ArrayList;
import java.util.EnumSet;

public class BusData {

    private static ArrayList<Bus> buses = new ArrayList<>();

    public static void createBusList(ArrayList<Bus> busList) {
        buses.addAll(busList);
    }

    public static ArrayList<Bus> possibleBuses(String startingLoc, String destination) {
        ArrayList<Bus> busOptions = new ArrayList<>();
        if (BusStops.isValidBusStop(startingLoc)) {
            BusStops.findBusStop(startingLoc).incrementSearchCount();
        }
        if (BusStops.isValidBusStop(destination)) {
            BusStops.findBusStop(destination).incrementSearchCount();
        }
        for (Bus bus : buses) {
            ArrayList<BusStops> route = bus.getPossibleRoute(startingLoc, destination);
            if (route != null) {
                busOptions.add(new Bus(bus.busNumber, route));
            }
        }
        return busOptions;
    }

    public static Bus selectBus(String busCode) throws NullPointerException {
        for (Bus bus : buses) {
            if (bus.getBusNumber().equals(busCode.toUpperCase())) {
                return bus;
            }
        }
        return null;
    }

    public static ArrayList<Bus> busAtStop(String busStop) {
        ArrayList<Bus> busList = new ArrayList<>();
        for (Bus bus : buses) {
            ArrayList<String> stopNames = bus.getStopNames();
            for (String name : stopNames) {
                if (name.equalsIgnoreCase(busStop)) {
                    busList.add(bus);
                    break;
                }
            }
        }
        return busList;
    }

    public static ArrayList<Bus> listOfAllBuses() {
        return buses;
    }

    public static ArrayList<Integer> getAllSearchCount() {
        ArrayList<Integer> allSearchCount = new ArrayList<Integer>();
        for (BusStops busStop : EnumSet.allOf(BusStops.class)) {
            allSearchCount.add(busStop.getSearchCount());
        }
        return allSearchCount;
    }
}
