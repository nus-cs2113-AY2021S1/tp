package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {

    private static Parser parser;
    private static ArrayList<Bus> busesInZoneA = new ArrayList<>();
    private static final String[] ZONE_A = {"AA1", "AA2"};

    private static final String[] AA1 = {
            "PGP",
            "Kent Ridge MRT Station",
            "Opp University Health Center",
            "Yusof Ishak House",
            "Central Library",
            "Kent Ridge",
            "Museum",
            "University Town",
            "University Health Centre",
            "Opp Kent RIdge MRT station",
            "PGPR"
    };

    private static final String[] AA2 = {
            "PGP",
            "Kent Ridge MRT Station",
            "Opp University Health Centre",
            "University Town",
            "Raffles Hall",
            "Kent Vale",
            "EA",
            "NUS IT",
            "University Health Centre",
            "Opp Kent Ridge MRT Station",
            "PGPR"
    };

    public Duke() {
        //open file
        //busesInZoneA = fileManager.loadData();
        busesInZoneA.add(new Bus("AA1", AA1));
        busesInZoneA.add(new Bus("AA1", AA2));
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