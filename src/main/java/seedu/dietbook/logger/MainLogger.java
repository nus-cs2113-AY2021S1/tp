package seedu.dietbook.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLogger {

    private final Logger logger;

    //@@author HengFuYuen-reused
    //Reused from https://stackoverflow.com/a/6315736 with minor modifications
    public MainLogger(String className) {
        logger = Logger.getLogger(className);
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.SEVERE);
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }
}
