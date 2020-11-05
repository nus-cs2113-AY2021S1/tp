package seedu.duke;

import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventLogger {

    private static Logger logger = Logger.getLogger("allEventLog");
    private static FileHandler fh;
    private static Ui userInterface;

    public static void initEventLogger(Ui ui) {
        String logging = "logging.txt";
        userInterface = ui;
        try {
            fh = new FileHandler(logging);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            logger.fine("Logger created");
        } catch (IOException e) {
            ui.printErrorMessage("log file was not created");
        }
    }


    /**
     * Getter function for returning the main logger to be used by other programs.
     *
     * @return Logger object that will log information to a logging.txt file
     */
    public static Logger getEventLogger() {
        return logger;
    }

}
