package seedu.duke.model.bus;

import java.util.ArrayList;
import java.util.Arrays;

public class Bus {

    String busNumber;
    ArrayList<BusStops> route = new ArrayList<>();

    public Bus(String busNo, BusStops[] stops) {
        busNumber = busNo;
        route.addAll(Arrays.asList(stops));
    }

    public Bus(String busNo, ArrayList<BusStops> stops) {
        busNumber = busNo;
        route.addAll(stops);
    }

    public ArrayList<String> getStopNames() {

        ArrayList<String> routeNames = new ArrayList<>();
        route.forEach(stop -> routeNames.add(stop.getName()));
        return routeNames;
    }

    public String getBusNumber() {
        return this.busNumber;
    }

    //@@author wamikamalik
    ArrayList<BusStops> getPossibleRoute(String startingLoc, String destination) {
        ArrayList<BusStops> allStopsFromStart = new ArrayList<>();
        ArrayList<BusStops> finalRoute = new ArrayList<>();
        ArrayList<String> allStopNamesFromStart = new ArrayList<>();
        ArrayList<String> routeNames = new ArrayList<>(getStopNames());
        routeNames.replaceAll(String::toLowerCase);

        if (routeNames.contains(startingLoc.toLowerCase())) {
            int startingIndex = routeNames.indexOf(startingLoc.toLowerCase());
            assert startingIndex != -1 : "Hmm, seems like the starting location is not in the list";
            int size = routeNames.size();
            assert size != 0 : "This bus route has no stops!";
            allStopNamesFromStart.addAll(routeNames.subList(startingIndex, size));
            allStopsFromStart.addAll(route.subList(startingIndex, size));
            if (allStopNamesFromStart.contains(destination.toLowerCase())) {
                int endIndex = allStopNamesFromStart.indexOf(destination.toLowerCase()) + 1;
                assert endIndex != -1 : "Hmm, seems like the destination is not in the list";
                finalRoute.addAll(allStopsFromStart.subList(0, endIndex));
            }
        }
        return finalRoute;
    }

    //@@author
    @Override
    public String toString() {
        if (route.size() > 0) {
            String printableRoute = String.join(" -> ", getStopNames());
            String output = "";
            while (printableRoute.length() > 100) {
                int index = printableRoute.lastIndexOf("->",100);
                output += printableRoute.substring(0, index) + "\n";
                printableRoute = printableRoute.substring(index).trim();
            }
            return busNumber + "\n" + output + printableRoute;
        }
        return null;
    }

}
