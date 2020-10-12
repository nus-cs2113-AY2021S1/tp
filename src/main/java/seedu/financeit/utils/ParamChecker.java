package seedu.financeit.utils;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InvalidCategoryException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.parser.DateTimeParser;
import seedu.financeit.ui.UiManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParamChecker {
    public static final String PARAM_DATE = "/date";
    public static final String PARAM_DAY = "/day";
    public static final String PARAM_TIME = "/time";
    public static final String PARAM_INDEX = "/id";
    public static final String PARAM_DESCRIPTION = "/desc";
    public static final String PARAM_CATEGORY = "/cat";
    public static final String PARAM_AMOUNT = "/amt";
    public static final String PARAM_NOTES = "/notes";
    public static final String PARAM_INC = "-i";
    public static final String PARAM_EXP = "-e";
    public static final String PARAM_AUTO = "-auto";
    public static Logger logger = Logger.getLogger(ParamChecker.class.getName());
    CommandPacket packet;

    public ParamChecker(CommandPacket packet) {
        this.packet = packet;
    }

    public LocalDate checkAndReturnDate(String paramType)
        throws ParseFailParamException {
        LocalDate date = null;
        boolean parseSuccess = false;

        logger.log(Level.INFO, "Checking date...");
        try {
            String rawDate = packet.getParam(paramType);
            if (rawDate.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            date = DateTimeParser.parseLocalDate(rawDate);
            parseSuccess = true;
        } catch (DateTimeException exception) {
            logger.log(Level.WARNING,
                String.format("Date parsed but not valid... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Not a valid date on the Gregorian Calendar!",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (InvalidParameterException exception) {
            logger.log(Level.WARNING,
                String.format("Date input cannot be parsed... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input format is not recognised.",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (EmptyParamException exception) {
            logger.log(Level.WARNING,
                String.format("No date input supplied... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Enter \"commands\" to check format!");
        }
        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return date;
    }

    public LocalTime checkAndReturnTime(String paramType)
        throws ParseFailParamException {
        LocalTime time = null;
        boolean parseSuccess = false;

        logger.log(Level.INFO, "Checking time...");
        try {
            String rawTime = packet.getParam(paramType);
            if (rawTime.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            time = DateTimeParser.parseLocalTime(rawTime);
            parseSuccess = true;
        } catch (DateTimeException exception) {
            logger.log(Level.WARNING,
                String.format("Time parsed but not valid... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Time is out of range!",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (InvalidParameterException exception) {
            logger.log(Level.WARNING,
                String.format("Time input cannot be parsed... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input format is not recognised.",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (EmptyParamException exception) {
            logger.log(Level.WARNING,
                String.format("No time input supplied... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Enter \"commands\" to check format!");
        }
        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return time;
    }

    public int checkAndReturnIndex(String paramType, ArrayList list) throws ParseFailParamException {
        String message;
        int index = -1;
        boolean parseSuccess = false;

        logger.log(Level.INFO, "Checking index validity...");
        if (list.size() == 0) {
            message = "There are no items in the list.";
        } else if (list.size() == 1) {
            message = "The only valid item index is 1.";
        } else {
            message = String.format("The range is from 1 to %d", list.size());
        }

        try {
            index = Integer.parseInt(packet.getParam(paramType));
            index = index - 1;
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException();
            }
            parseSuccess = true;
        } catch (IndexOutOfBoundsException exception) {
            logger.log(Level.WARNING,
                String.format("Index out of bounds... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                String.format("Index input \"%s\" is out of bounds!", index),
                message);
        } catch (NumberFormatException exception) {
            logger.log(Level.WARNING,
                String.format("Index cannot be parsed... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Cannot parse your input. Please enter a positive integer!",
                message);
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }

        return index;
    }

    public double checkAndReturnDouble(String paramType) throws ParseFailParamException {
        String input = packet.getParam(paramType);
        boolean parseSuccess = false;
        double output = -1;

        logger.log(Level.INFO, "Checking input Double...");
        try {
            output = Double.parseDouble(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            logger.log(Level.WARNING,
                String.format("Double not recognised... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Cannot parse your input. Please enter valid 2 d.p input!");
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public int checkAndReturnInt(String paramType) throws ParseFailParamException {
        String input = packet.getParam(paramType);
        boolean parseSuccess = false;
        int output = -1;

        logger.log(Level.INFO, "Checking input Integer...");
        try {
            output = Integer.parseInt(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            logger.log(Level.WARNING,
                String.format("Int not recognised... Err: %s", exception.getMessage()));

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Cannot parse your input. Please enter valid integer input!");
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public String checkAndReturnCategory(String paramType) throws ParseFailParamException {
        boolean parseSuccess = false;
        String category = packet.getParam(paramType);

        logger.log(Level.INFO, "Checking input Category...");
        try {
            if (!Constants.categoryMap.containsKey(category)) {
                throw new InvalidCategoryException(category);
            }
            parseSuccess = true;
        } catch (InvalidCategoryException exception) {
            logger.log(Level.WARNING, "Category not recognised...");

            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Input \"exp cat\" to show valid categories!");
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return category;
    }

    public String getUnrecognizedParamMessage(String paramType) {
        return String.format("The param type is not recognized: \"%s\"", paramType);
    }

    public String getParseFailParamMessage(String paramType) {
        return String.format("The param type cannot be parsed: \"%s\"", paramType);
    }
}
