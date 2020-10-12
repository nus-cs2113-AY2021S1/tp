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

    public Bus(String busNo, ArrayList<String> route) {
        busNumber = busNo;
        this.route.addAll(route);
    }

    ArrayList<String> getPossibleRoute(String startingLoc, String destination) {

        ArrayList<String> allStopsFromStart = new ArrayList<>();
        ArrayList<String> finalRoute = new ArrayList<>();
        if (route.contains(startingLoc)) {
            int startingIndex = route.indexOf(startingLoc) + 1;
            int size = route.size();
            allStopsFromStart.addAll(route.subList(startingIndex, size));
            if (allStopsFromStart.contains(destination)) {
                int endIndex = allStopsFromStart.indexOf(destination);
                finalRoute.addAll(allStopsFromStart.subList(0, endIndex));
            }
        }
        return finalRoute;

    }

    @Override
    public String toString() {
        String printableRoute = String.join("-> ", route);
        return busNumber + ":\n\t" + printableRoute;
    }
}
