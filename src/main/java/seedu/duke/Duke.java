package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.ui.Ui;

public class Duke {

    private static Parser parser;

    public Duke() {
        parser = new Parser();
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
                parser.getCommand();
                isOngoing = parser.extractType();
            } catch (CustomException error) {
                Ui.showError(error);
            }
        }
    }

}