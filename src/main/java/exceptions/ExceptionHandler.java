package exceptions;

import logger.SchwarzeneggerLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author tienkhoa16
/**
 * A class that deals with handling exceptions.
 */
public class ExceptionHandler {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    /**
     * Handles checked exceptions.
     *
     * @param e Checked exceptions in The Schwarzenegger.
     * @return Error message.
     */
    public static String handleCheckedExceptions(SchwarzeneggerException e) {
        logger.log(Level.WARNING, "handling Schwarzenegger Exception: " + e.toString());
        return e.getMessage();
    }

    /**
     * Handles unchecked exceptions.
     *
     * @param e Unchecked exception in Duke.
     * @return Error message.
     */
    public static String handleUncheckedExceptions(Exception e) {
        logger.log(Level.WARNING, "handling unchecked exception: " + e.toString());
        return e.toString();
    }
}
