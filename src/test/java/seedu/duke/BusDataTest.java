package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusDataTest {

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

    private static final BusStops[] AA2 = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.UNIVERSITYTOWN,
        BusStops.RAFFLESHALL,
        BusStops.KENTVALE,
        BusStops.EA,
        BusStops.NUSIT,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    static ArrayList<Bus> busesInZoneA = new ArrayList<>(Arrays.asList(new Bus("AA1", AA1),
            new Bus("AA2", AA2)));

    @BeforeAll
    public static void makeList() {
        BusData.createBusList(busesInZoneA);
    }

    @Test
    void possibleBuses_noRoute_returnEmptyBusArrayList() {
        ArrayList<Bus> routes = new ArrayList<>();
        ArrayList<BusStops> stops = new ArrayList<>();
        routes.add(new Bus("AA1", stops));
        routes.add(new Bus("AA2", stops));
        String startingLoc = "centrAL LibraRy";
        String destination = "Pgp";
        assertEquals(routes.toString(), BusData.possibleBuses(startingLoc, destination).toString());
    }

    @Test
    void possibleBuses_oneRoute_returnBusArrayList() {
        ArrayList<Bus> routes = new ArrayList<>();
        ArrayList<BusStops> stops = new ArrayList<>();
        routes.add(new Bus("AA1", stops));
        stops.addAll(Arrays.asList(AA2).subList(7, 9));
        routes.add(new Bus("AA2", stops));
        String startingLoc = "Ea";
        String destination = "Opp kent ridge MRT station";
        ArrayList<Bus> busOptionsOne = new ArrayList<>(BusData.possibleBuses(startingLoc, destination));
        assertEquals(routes.toString(), busOptionsOne.toString());
    }

    @Test
    void possibleBuses_manyRoutes_returnBusArrayList() {
        ArrayList<Bus> routes = new ArrayList<>();
        ArrayList<BusStops> stopsAa1 = new ArrayList<>(Arrays.asList(AA2).subList(8, 10));
        routes.add(new Bus("AA1", stopsAa1));
        ArrayList<BusStops> stopsAa2 = new ArrayList<>(Arrays.asList(AA2).subList(4, 10));
        routes.add(new Bus("AA2", stopsAa2));
        String startingLoc = "UniverSity Town";
        String destination = "PGPr";
        ArrayList<Bus> busOptions = new ArrayList<>(BusData.possibleBuses(startingLoc, destination));
        assertEquals(routes.toString(), busOptions.toString());
    }

}