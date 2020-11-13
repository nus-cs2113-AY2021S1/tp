package seedu.duke.model.bus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTest {

    private static final BusStops[] AA1 = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.YUSOFISHAKHOUSE,
        BusStops.CENTRALLIBRARY,
        BusStops.KENTRIDGE,
        BusStops.MUSEUM,
        BusStops.UNIVERSITYTOWN,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    @Test
    void getPossibleRoute_routeExists_returnRouteArrayList() {
        String startingLoc = "centrAL LibraRy";
        String destination = "University health cenTre";
        Bus newBus = new Bus("AA1", AA1);
        ArrayList<BusStops> route = new ArrayList<>(Arrays.asList(AA1).subList(4, 9));
        assertEquals(route, newBus.getPossibleRoute(startingLoc, destination));
    }

    @Test
    void getPossibleRoute_routeExistsNoIntermediateStops_returnRouteArrayList() {
        String startingLoc = "Museum";
        String destination = "University town";
        Bus newBus = new Bus("AA1", AA1);
        ArrayList<BusStops> route = new ArrayList<>(Arrays.asList(AA1).subList(6, 8));
        assertEquals(route, newBus.getPossibleRoute(startingLoc, destination));
    }

    @Test
    void getPossibleRoute_noRoute_destinationNotInList_returnEmptyRouteArrayList() {
        String startingLoc = "centrAL LibraRy";
        String destination = "Pgp";
        Bus newBus = new Bus("AA1", AA1);
        ArrayList<BusStops> route = new ArrayList<>();
        assertEquals(route, newBus.getPossibleRoute(startingLoc, destination));
        assertEquals(0, newBus.getPossibleRoute(startingLoc, destination).toArray().length);
    }

    @Test
    void getPossibleRoute_noRoute_startNotInList_returnEmptyRouteArrayList() {
        String startingLoc = "University Hall";
        String destination = "Pgp";
        Bus newBus = new Bus("AA1", AA1);
        ArrayList<BusStops> route = new ArrayList<>();
        assertEquals(route, newBus.getPossibleRoute(startingLoc, destination));
        assertEquals(0, newBus.getPossibleRoute(startingLoc, destination).toArray().length);
    }

    @Test
    void getPossibleRoute_noRoute_startAndDestinationInList_wrongOrder_returnEmptyRouteArrayList() {
        String startingLoc = "Pgpr";
        String destination = "Pgp";
        Bus newBus = new Bus("AA1", AA1);
        ArrayList<BusStops> route = new ArrayList<>();
        assertEquals(route, newBus.getPossibleRoute(startingLoc, destination));
        assertEquals(0, newBus.getPossibleRoute(startingLoc, destination).toArray().length);
    }
}