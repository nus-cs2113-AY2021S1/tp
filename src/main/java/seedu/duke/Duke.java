package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.storage.FavStorage;
import seedu.duke.storage.FreqStorage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {

    private static Parser parser;
    public static FavStorage favFile = new FavStorage("data/FavList.txt");
    public static FreqStorage freqFile = new FreqStorage("data/freqList.txt");
    private static BusInfo busInfo = new BusInfo();

    public Duke() {
        RouteParser.initLogger();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        new Duke();
        boolean isOngoing = true;
        //System.out.println(BusData.getAllSearchCount());
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
