package seedu.duke.model.bus;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Represents the list of all shuttle buses in NUS with their routes.
 */
public class BusData {

    private static ArrayList<Bus> buses = new ArrayList<>();

    public static void createBusList(ArrayList<Bus> busList) {
        buses.clear();
        buses.addAll(busList);
    }

    /**
     * Returns all buses with their intermediate routes that go from the startingLoc to the destination.
     *
     * @param startingLoc the location to start at.
     * @param destination the location to be reached.
     * @return all possible buses that can be taken with the intermediate stops for each.
     */
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

    /**
     * Searches and select bus that matches name.
     *
     * @param busCode bus code input to search with
     * @return bus Bus object, if found
     */
    public static Bus selectBus(String busCode) {
        if (buses.size() > 0) {
            for (Bus bus : buses) {
                if (bus.getBusNumber().equals(busCode.toUpperCase())) {
                    return bus;
                }
            }
        }
        return null;
    }

    public static ArrayList<Bus> getBusAtStop(String busStop) {
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
