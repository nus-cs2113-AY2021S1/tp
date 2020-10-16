package seedu.financeit.utils;

import seedu.financeit.common.CategoryMap;
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

/**
 * Class that handles error handling of params
 * and throws only ParseFailParamsException to be handled in
 * the user classes.
 */

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
    private static String errorMessage;

    public ParamChecker(CommandPacket packet) {
        this.packet = packet;
    }

    private void printErrorMessage() {
        System.out.println(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void clearErrorMessage() {
        errorMessage = "";
    }

    public LocalDate checkAndReturnDate(String paramType)
        throws ParseFailParamException {
        LocalDate date = null;
        boolean parseSuccess = false;

        clearErrorMessage();

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

            errorMessage = getErrorMessageDateDateTimeException();
        } catch (InvalidParameterException exception) {
            logger.log(Level.WARNING,
                String.format("Date input cannot be parsed... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageDateInvalidFormat();
        } catch (EmptyParamException exception) {
            logger.log(Level.WARNING,
                String.format("No date input supplied... Err: %s", exception.getMessage()));

            errorMessage = UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Enter \"commands\" to check format!");
        } finally {
            printErrorMessage();
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

        clearErrorMessage();

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

            errorMessage = getErrorMessageTimeDateTimeException();
        } catch (InvalidParameterException exception) {
            logger.log(Level.WARNING,
                String.format("Time input cannot be parsed... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageTimeInvalidFormat();
        } catch (EmptyParamException exception) {
            logger.log(Level.WARNING,
                String.format("No time input supplied... Err: %s", exception.getMessage()));

            errorMessage = UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Enter \"commands\" to check format!");
        } finally {
            printErrorMessage();
        }
        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return time;
    }

    /**
     * Checks if user-inputted index is valid, and converts it to zero-based index form.
     * @param paramType - paramType that has index as paramArgument
     * @param list - list of items to select from
     * @return zero-based index (i.e. user-inputted index - 1)
     * @throws ParseFailParamException If index was not parsed successfully,
     *                                 for example due to error thrown
     */
    public int checkAndReturnIndex(String paramType, ArrayList list) throws ParseFailParamException {
        String message;
        int index = -1;
        boolean parseSuccess = false;

        clearErrorMessage();

        logger.log(Level.INFO, "Checking index validity...");

        if (list.size() == 0) {
            message = getMessageNoItemsInList();
        } else if (list.size() == 1) {
            message = getMessageOneItemInList();
        } else {
            message = getMessageListRangeIndex(list.size());
        }

        try {
            index = Integer.parseInt(packet.getParam(paramType));
            if (index < 1 || index > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            parseSuccess = true;
        } catch (IndexOutOfBoundsException exception) {
            logger.log(Level.WARNING,
                String.format("Index out of bounds... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageListIndexOutOfBounds(message, index);
        } catch (NumberFormatException exception) {
            logger.log(Level.WARNING,
                String.format("Index cannot be parsed... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageListNumberFormatException(message);
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }

        return index - 1;
    }

    public double checkAndReturnDouble(String paramType) throws ParseFailParamException {
        String input = packet.getParam(paramType);
        boolean parseSuccess = false;
        double output = -1;

        clearErrorMessage();

        logger.log(Level.INFO, "Checking input Double...");
        try {
            output = Double.parseDouble(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            logger.log(Level.WARNING,
                String.format("Double not recognised... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageDoubleNumberFormatException();
        } finally {
            printErrorMessage();
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

        clearErrorMessage();

        logger.log(Level.INFO, "Checking input Integer...");
        try {
            output = Integer.parseInt(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            logger.log(Level.WARNING,
                String.format("Int not recognised... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageNumberFormatException();
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }



    public String checkAndReturnCategory(String paramType) throws ParseFailParamException {
        boolean parseSuccess = false;
        String category = packet.getParam(paramType);

        clearErrorMessage();

        logger.log(Level.INFO, "Checking input Category...");
        try {
            if (! CategoryMap.inputToCategoryMap.containsKey(category)) {
                throw new InvalidCategoryException(category);
            }
            parseSuccess = true;
        } catch (InvalidCategoryException exception) {
            logger.log(Level.WARNING, "Category not recognised...");

            errorMessage = getErrorMessageInvalidCategoryException(exception);
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return category;
    }

    /**
     * List of error messages that are referenced in the above functions.
     */

    public static String getErrorMessageDateDateTimeException() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Not a valid date on the Gregorian Calendar!",
            "Check your input again against the following format!",
            "Date format: YYMMDD");
    }

    public static String getErrorMessageDateInvalidFormat() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Input format is not recognised.",
            "Check your input again against the following format!",
            "Date format: YYMMDD");
    }

    public static String getErrorMessageTimeInvalidFormat() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Input format is not recognised.",
            "Check your input again against the following format!",
            "Time format: HHMM");
    }

    public static String getErrorMessageTimeDateTimeException() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Time is out of range!",
            "Check your input again against the following format!",
            "Time format: HHMM");
    }

    public static String getMessageListRangeIndex(int size) {
        return String.format("The range is from 1 to %d", size);
    }

    public String getMessageOneItemInList() {
        return "The only valid item index is 1.";
    }

    public String getMessageNoItemsInList() {
        return "There are no items in the list.";
    }

    public static String getErrorMessageListNumberFormatException(String message) {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Cannot parse your input. Please enter a positive integer!",
            message);
    }

    public static String getErrorMessageListIndexOutOfBounds(String message, int index) {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            getMessageListRangeIndex(index),
            message);
    }

    public static String getErrorMessageDoubleNumberFormatException() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Cannot parse your input. Please enter valid 2 d.p input!");
    }

    public static String getErrorMessageNumberFormatException() {
        return UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Cannot parse your input. Please enter valid integer input!");
    }

    public static String getErrorMessageInvalidCategoryException(InvalidCategoryException exception) {
        return errorMessage = UiManager.getStringPrintWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            exception.getMessage(),
            "Input \"exp cat\" to show valid categories!");
    }

    public String getUnrecognizedParamMessage(String paramType) {
        return String.format("The param type is not recognized: \"%s\"", paramType);
    }

    public String getParseFailParamMessage(String paramType) {
        return String.format("The param type cannot be parsed: \"%s\"", paramType);
    }
}
