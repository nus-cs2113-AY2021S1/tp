package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static Parser parser;
    private static ArrayList<Bus> busesInZoneA = new ArrayList<>();
    private static final String[] ZONE_A = {"AA1", "AA2"};

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

    public Duke() {
        //open file
      
        busesInZoneA.add(new Bus(ZONE_A[0], AA1));
        busesInZoneA.add(new Bus(ZONE_A[1], AA2));
        BusData.createBusList(busesInZoneA);

    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        new Duke();
        boolean isOngoing = true;
        Ui.printWelcomeMessage();
        while (isOngoing) {
            try {
                String fullCommand = Ui.getCommand();
                parser = new Parser(fullCommand);
                isOngoing = parser.extractType();
            } catch (CustomException error) {
                Ui.showError(error);
            }
        }
    }

}
