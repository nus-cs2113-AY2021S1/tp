package seedu.duke;

import seedu.duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static void clearLog() {
        String[] pathName = {"logging.txt"};
        Path fileName = createPath(pathName);
        ArrayList<String> toBeWritten = new ArrayList<>();
        try {
            Files.write(fileName, toBeWritten);
        } catch (IOException e) {
            userInterface.printErrorMessage("log cannot be cleared");
        }
    }

    /**
     * Function accepts a string and creates a path object originating from the user directory.
     *
     * @param pathName is a string array which accepts in the path name words, each word represents a folder
     * @return Path object indicating the location of the pathName keyed in initially.
     */
    private static Path createPath(String[] pathName) {

        String origin = System.getProperty("user.dir");
        Path newPath = Paths.get(origin, pathName);
        return newPath;
    }

}
