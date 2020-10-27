package seedu.duke.logic.commands.buscommand;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.model.bus.BusStops;
import seedu.duke.exceptions.CustomException;

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

    @BeforeAll
    public static void makeList() {
        busesInZoneA.add(new Bus("AA1", AA1));
        BusData.createBusList(busesInZoneA);
    }

    @Test
    void executeCommand_selectBus_success() {
        Bus bus = BusData.selectBus("AA1");
        assertEquals("AA1", bus.getBusNumber());
    }

    @Test
    void executeCommand_printRoute_success() {
        String fullBusRoute = "AA1\n"
                + "PGP -> Kent Ridge MRT Station -> Opp University Health Centre -> Yusof Ishak House \n-> "
                + "Central Library -> Kent Ridge -> Museum -> University Town -> "
                + "University Health Centre \n-> Opp Kent Ridge MRT station -> PGPR";
        Bus bus = BusData.selectBus("AA1");
        assertEquals(fullBusRoute, bus.toString());
    }

    @Test
    void executeCommand_missingBusCode_expectException() {
        String locations = "";
        RouteCommand com = new RouteCommand(locations);
        assertThrows(CustomException.class, com::executeCommand);
    }

    @Test
    void executeCommand_invalidBusCode_expectException() {
        String locations = "AA3";
        RouteCommand com = new RouteCommand(locations);
        assertThrows(CustomException.class, com::executeCommand);
    }

}