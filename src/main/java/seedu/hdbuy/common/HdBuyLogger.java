package seedu.hdbuy.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HdBuyLogger {

    private static final Logger logger = Logger.getGlobal();

    public static void enableLogger(boolean isEnabled) {
        if (!isEnabled) {
            logger.setLevel(Level.OFF);
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }
}
