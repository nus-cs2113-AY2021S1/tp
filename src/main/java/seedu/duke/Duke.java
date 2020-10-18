package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {

    private static Parser parser;
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
