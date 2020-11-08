package seedu.dietbook.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a logger that other classes can utilise to minimise code duplications.
 * A <code>MainLogger</code> object creates a logger and console handler and sets their level.
 */
public class MainLogger {

    private final Logger logger;

    //@@author HengFuYuen-reused
    //Reused from https://stackoverflow.com/a/6315736 with minor modifications

    /**
     * Constructs a <code>MainLogger</code> given the name of the class that uses the logger.
     *
     * @param className The name of the class that uses the logger.
     */
    public MainLogger(String className) {
        assert className != null : "Class name cannot be null";
        assert className.trim().length() > 0 : "Class name cannot be an empty string";

        logger = Logger.getLogger(className);
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.SEVERE);
    }

    /**
     * Logs into the console handler given a message level identifier and message.
     *
     * @param level A message level identifier.
     * @param message The string message to log.
     */
    public void log(Level level, String message) {
        assert level != null : "Logging level should not be null";
        assert message != null : "Log message should not be null";
        assert message.trim().length() > 0 : "Log message should not be an empty string";

        logger.log(level, message);
    }
}
