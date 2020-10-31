package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DescFavParser extends Parser {

    public static final String DELIMITER = "/to";
    private static final Logger logger = Logger.getLogger(RouteParser.class.getName());
    private int index;
    private String description;

    public DescFavParser(String message) {
        super(message);
    }

    public void parseInput() throws CustomException {
        try {
            if (super.getUserInput().trim().length() == 0) {
                logger.warning("User input is empty.");
                throw new CustomException(ExceptionType.NO_INPUT);
            } else if (super.getUserInput().contains(DELIMITER)) {
                String[] components = super.splitCommands(2, DELIMITER);
                checkComponents(components);
                index = checkIndex(components[0]);
                description = checkDescription(components[1]);
            } else {
                logger.warning("Delimiter /to is missing.");
                throw new CustomException(ExceptionType.NO_DELIMITER_DESCFAV);
            }
        } catch (StringIndexOutOfBoundsException error) {
            logger.warning("Either of the parameters is missing.");
            throw new CustomException(ExceptionType.NO_INPUT);
        }
    }

    private void checkComponents(String[] components) throws CustomException {
        if (components[0].trim().isEmpty() & components[1].trim().isEmpty()) {
            logger.warning("All parameters are missing.");
            throw new CustomException(ExceptionType.NO_INPUT);
        }
        assert !(components[0].trim().isEmpty() && components[1].trim().isEmpty()) : "Component 1 and 2 are empty.";
        if (components[0].contains(DELIMITER) | components[1].contains(DELIMITER)) {
            throw new CustomException(ExceptionType.MANY_DELIMITERS_DESCFAV);
        }
    }

    private String checkDescription(String description) throws CustomException {
        if (description.trim().isBlank() | description.trim().isEmpty()) {
            logger.warning("Description is missing.");
            throw new CustomException(ExceptionType.EMPTY_DESCRIPTION);
        }
        assert !description.trim().isBlank() : "Description has only spaces.";
        assert !description.trim().isEmpty() : "Description is empty.";
        return description.trim();
    }

    private int checkIndex(String index) throws CustomException {
        try {
            int rawIndex = Integer.parseInt(index.trim());
            return rawIndex;
        } catch (NumberFormatException error) {
            logger.warning("Given index is not a number.");
            throw new CustomException(ExceptionType.NOT_A_NUMBER);
        }
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
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
