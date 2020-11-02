package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class RouteParser extends Parser {

    public static final String DELIMITER = "/to";
    private static final Logger logger = Logger.getLogger(RouteParser.class.getName());

    public RouteParser(String message) {
        super(message);
    }

    public String[] getLocations() throws CustomException {
        try {
            if (super.getUserInput().trim().length() == 0) {
                logger.warning("User input is empty.");
                throw new CustomException(ExceptionType.NO_LOCATIONS);
            } else if (super.getUserInput().contains(DELIMITER)) {
                String[] components = super.splitCommands(2, DELIMITER);
                checkTooManyDelimiters(components);
                return new String[]{components[0], components[1]};
            } else {
                logger.warning("Delimiter /to is missing.");
                throw new CustomException(ExceptionType.NO_DELIMITER_ROUTE);
            }
        } catch (StringIndexOutOfBoundsException error) {
            logger.warning("Either of the locations is missing");
            throw new CustomException(ExceptionType.NO_LOCATIONS);
        }
    }

    private void checkTooManyDelimiters(String[] components) throws CustomException {
        if (components[0].contains(DELIMITER) | components[1].contains(DELIMITER)) {
            logger.warning("Too many delimiters \"/to\".");
            throw new CustomException(ExceptionType.MANY_DELIMITERS_ROUTE);
        }
    }

    public static void initLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("logger.log");
            fh.setLevel(Level.WARNING);
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.severe("Error in creating logger file.");
        }
    }

}
