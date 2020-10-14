package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.BusStops;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteMapCommandTest {
    private static ArrayList<Bus> busesInZoneA = new ArrayList<>();
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
    void executeCommand_success() {
        busesInZoneA.add(new Bus("AA1", AA1));
        BusData.createBusList(busesInZoneA);
        Bus bus = BusData.selectBus("AA1");
        assertEquals("AA1", bus.getBusNumber());
    }

}