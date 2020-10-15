package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.BusStops;
import seedu.duke.exceptions.CustomException;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void executeCommand_selectBus_success() {
        busesInZoneA.add(new Bus("AA1", AA1));
        BusData.createBusList(busesInZoneA);
        Bus bus = BusData.selectBus("AA1");
        assertEquals("AA1", bus.getBusNumber());
    }

    @Test
    void executeCommand_printRoute_success() {
        String fullBusRoute = "AA1\n"
                + "PGP -> Kent Ridge MRT Station -> Opp University Health Centre -> Yusof Ishak House -> "
                + "Central Library -> Kent Ridge -> Museum -> University Town -> "
                + "University Health Centre -> Opp Kent Ridge MRT station -> PGPR";
        BusData.createBusList(busesInZoneA);
        Bus bus = BusData.selectBus("AA1");
        assertEquals(fullBusRoute, bus.toString());
    }

}