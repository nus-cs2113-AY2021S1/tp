package seedu.financeit.utils;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InvalidCategoryException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.parser.DateTimeParser;
import seedu.financeit.ui.UiManager;

import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    // Maximum amount of money that can be inputed: 100 digits including floating point + 1 char for decimal point
    private static final int MAX_INPUT_DOUBLE_LENGTH = 101;

    private static CommandPacket packet;
    private static String errorMessage;
    private static ParamChecker paramChecker = null;

    private ParamChecker() {
    }

    public static ParamChecker getInstance() {
        if (paramChecker == null) {
            paramChecker = new ParamChecker();
        }
        return paramChecker;
    }

    public void setPacket(CommandPacket packet) {
        this.packet = packet;
    }

    private void printErrorMessage() {
        System.out.print(errorMessage);
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

        LoggerCentre.loggerParamChecker.info("Checking date...");
        try {
            String rawDate = packet.getParam(paramType);
            if (rawDate.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            date = DateTimeParser.parseLocalDate(rawDate);
            parseSuccess = true;
        } catch (DateTimeException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Date parsed but not valid... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageDateDateTimeException();
        } catch (InvalidParameterException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Date input cannot be parsed... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageDateInvalidFormat();
        } catch (EmptyParamException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("No date input supplied... Err: %s", exception.getMessage()));

            errorMessage = UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
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

        LoggerCentre.loggerParamChecker.info("Checking time...");
        try {
            String rawTime = packet.getParam(paramType);
            if (rawTime.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            time = DateTimeParser.parseLocalTime(rawTime);
            parseSuccess = true;
        } catch (DateTimeException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Time parsed but not valid... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageTimeDateTimeException();
        } catch (InvalidParameterException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Time input cannot be parsed... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageTimeInvalidFormat();
        } catch (EmptyParamException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("No time input supplied... Err: %s", exception.getMessage()));

            errorMessage = UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
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

    public int checkAndReturnDayOfMonth(String paramType) throws ParseFailParamException {
        int dayOfMonth = -1;
        boolean parseSuccess = false;

        clearErrorMessage();

        LoggerCentre.loggerParamChecker.info("Checking day of month...");
        try {
            dayOfMonth = checkAndReturnInt(paramType);
            if (dayOfMonth < 1 || dayOfMonth > 31) {
                throw new NumberFormatException();
            }

            /*
            Checks if there are any months that do not have the specified dayOfMonth
            e.g. Feb does not have day 29, 30 or 31.
             */
            String[] monthsWithoutDay = DateTimeHelper.monthsWithoutDayOfMonth(dayOfMonth);

            if (monthsWithoutDay.length >= 1) {
                String monthMaxDayReminder = String.format("The following month(s) do not have day %s: "
                        + Arrays.toString(monthsWithoutDay), dayOfMonth);
                String noReminderMessage = "There will NOT be any reminder for this entry during these month(s).";
                UiManager.printWithStatusIcon(Common.PrintType.REMINDER,
                        monthMaxDayReminder, noReminderMessage);
            }

            parseSuccess = true;
        } catch (NumberFormatException exception) {
            LoggerCentre.loggerParamChecker.warning("Day of month supplied is not between 1 and 31!");

            errorMessage = getErrorMessageDayOfMonthOutOfBounds();

            /*
            Print error message here instead of in a finally block.
            This is to prevent double printing of error message,
            in case checkAndReturnInt encounters an error.
             */
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return dayOfMonth;
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

        LoggerCentre.loggerParamChecker.info("Checking index validity...");

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
            LoggerCentre.loggerParamChecker.warning(
                String.format("Index out of bounds... Err: %s", exception.getMessage()));

            errorMessage = getErrorMessageListIndexOutOfBounds(message);
        } catch (NumberFormatException exception) {
            LoggerCentre.loggerParamChecker.warning(
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

        LoggerCentre.loggerParamChecker.info("Checking input Double...");
        input = input.replaceAll("[^\\w | .]", "");
        try {
            output = Double.parseDouble(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Double not recognised... Err: %s", exception.getMessage()));
            errorMessage = getErrorMessageDoubleNumberFormatException(input, errorMessage);
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public double checkAndReturnDoubleSigned(String paramType) throws ParseFailParamException {
        String input = packet.getParam(paramType);
        boolean parseSuccess = false;
        double output = -1;

        clearErrorMessage();
        LoggerCentre.loggerParamChecker.info("Checking input Double...");
        try {
            if (RegexMatcher.alphabetMatcher(input).find()) {
                throw new NumberFormatException();
            }
            input = input.replaceAll("[^\\w | [-.]]", "");
            if (input.length() > MAX_INPUT_DOUBLE_LENGTH) {
                throw new NumberFormatException();
            }
            output = Double.parseDouble(input);

            //Truncate double to 2 d.p.
            DecimalFormat bd = new DecimalFormat("#.##");
            bd.setRoundingMode(RoundingMode.CEILING);
            output = Double.parseDouble(bd.format(output));

            if (output < 0) {
                throw new NumberFormatException();
            }
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            if (input.length() > MAX_INPUT_DOUBLE_LENGTH) {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Expected input out of bounds... Err: %s", exception.getMessage()));
                errorMessage = "Amount provided is too long in length! "
                    + "Maximum amount is of 100 digits long.";
            } else if (output < 0) {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Expected positive double... Err: %s", exception.getMessage()));
                errorMessage = "Expected a positive number.";
            } else {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Double not recognised... Err: %s", exception.getMessage()));
            }
            errorMessage = getErrorMessageDoubleNumberFormatException(input, errorMessage);
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

        LoggerCentre.loggerParamChecker.info("Checking input Integer...");
        try {
            output = Integer.parseInt(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            if (paramType.length() > (int)Math.log(Long.MAX_VALUE) + 1) {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Int format is too long... Err: %s", exception.getMessage()));
                errorMessage = "Input value is too out of range: 9,223,372,036,854,775,807";
            } else {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Int not recognised... Err: %s", exception.getMessage()));
            }
            errorMessage = getErrorMessageNumberFormatException(input, errorMessage);
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public String checkAndReturnDescription(String paramType) throws ParseFailParamException{
        String input = packet.getParam(paramType);
        if (input.contains(";")) {
            errorMessage = "\n\"; is an illegal character in this program. Try again without the character.\"\n";
            LoggerCentre.loggerParamChecker.warning(
                errorMessage);
            throw new ParseFailParamException(paramType);
        }
        return input;
    }

    public int checkAndReturnIntSigned(String paramType) throws ParseFailParamException {
        String input = packet.getParam(paramType);
        boolean parseSuccess = false;
        int output = -1;

        clearErrorMessage();

        LoggerCentre.loggerParamChecker.info("Checking input Integer...");
        try {
            output = Integer.parseInt(input);
            if (output < 0) {
                throw new NumberFormatException();
            }
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            if (paramType.length() > (int)Math.log(Long.MAX_VALUE) + 1) {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Int format is too long... Err: %s", exception.getMessage()));
                errorMessage = "\nInput value is too out of range: 9,223,372,036,854,775,807\n";
            } else if (output < 0) {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Expected positive integer... Err: %s", exception.getMessage()));
                errorMessage = "\nExpected Positive integer!\n";
            } else {
                LoggerCentre.loggerParamChecker.warning(
                    String.format("Int not recognised... Err: %s", exception.getMessage()));

            }
            errorMessage = getErrorMessageNumberFormatException(input, errorMessage);
        } finally {
            printErrorMessage();
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public void checkAndReturnDuplicateParamTypes(String paramType, HashMap paramMap)
        throws ParseFailParamException {
        LoggerCentre.loggerParamChecker.info("Params: " + paramMap);
        LoggerCentre.loggerParamChecker.info("ParamType: " + paramType);
        if (paramMap.containsKey(paramType)) {
            LoggerCentre.loggerParamChecker.warning(
                String.format("Duplicate param detected..."));
            errorMessage = getMessageMultipleParamToParamType(paramType, paramMap);
            printErrorMessage();
            throw new ParseFailParamException(paramType);
        }
    }



    public String checkAndReturnCategory(String paramType) throws ParseFailParamException {
        boolean parseSuccess = false;
        String category = packet.getParam(paramType);

        clearErrorMessage();

        LoggerCentre.loggerParamChecker.info("Checking input Category...");
        try {
            if (! CategoryMap.inputToCategoryMap.containsKey(category)) {
                throw new InvalidCategoryException(category);
            }
            parseSuccess = true;
        } catch (InvalidCategoryException exception) {
            LoggerCentre.loggerParamChecker.warning("Category not recognised...");

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
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Not a valid date on the Gregorian Calendar!",
            "Check your input again against the following format!",
            "Date format: YYMMDD");
    }

    public static String getErrorMessageDateInvalidFormat() {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Input format is not recognised.",
            "Check your input again against the following format!",
            "Date format: YYMMDD");
    }

    public static String getErrorMessageTimeInvalidFormat() {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Input format is not recognised.",
            "Check your input again against the following format!",
            "Time format: HHMM");
    }

    public static String getErrorMessageTimeDateTimeException() {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Time is out of range!",
            "Check your input again against the following format!",
            "Time format: HHMM");
    }

    public static String getErrorMessageDayOfMonthOutOfBounds() {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                "Day of month is out of range! Please enter an integer between 1 and 31");
    }

    public static String getMessageListRangeIndex(int size) {
        return String.format("Valid range is from 1 to %d", size);
    }

    public String getMessageOneItemInList() {
        return "The only valid item index is 1.";
    }

    public String getMessageNoItemsInList() {
        return "There are no items in the list.";
    }

    public static String getErrorMessageListNumberFormatException(String message) {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Cannot parse your input. Please enter a positive integer!",
            message);
    }

    public static String getErrorMessageListIndexOutOfBounds(String message) {
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            message);
    }

    /**
     * Error message for invalid double inputs.
     * @param input Raw input
     * @param message Pre-existing error messages to print. Pass in empty string if none.
     * @return Error message in a formatted string for printing
     */
    public static String getErrorMessageDoubleNumberFormatException(String input, String message) {
        String errorToPrint = String.format("Invalid input: \"%s\". Please enter a valid number!", input);
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            errorToPrint, message);
    }

    /**
     * Error message for invalid integer inputs.
     * @param input Raw input
     * @param message Pre-existing error messages to print. Pass in empty string if none.
     * @return Error message in a formatted string for printing
     */
    public static String getErrorMessageNumberFormatException(String input, String message) {
        String errorToPrint = String.format("Invalid input: \"%s\". Please enter a valid integer!", input);
        return UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            errorToPrint, message);
    }

    public static String getErrorMessageInvalidCategoryException(InvalidCategoryException exception) {
        return errorMessage = UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            exception.getMessage(),
            "Input \"exp cat\" to show valid categories!");
    }

    public static String getMessageMultipleParamToParamType(String paramType, HashMap params) {
        return errorMessage = UiManager.getStringPrintWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
            "Multiple params supplied to the same paramType is not allowed!",
            "The first instance of the param is accepted: " + params.get(paramType));
    }

    public String getUnrecognizedParamMessage(String paramType) {
        return String.format("The param type is not recognized: \"%s\". "
                + "paramArgument (if any) for this param will be ignored.", paramType);
    }

    public String getParseFailParamMessage(String paramType) {
        return String.format("The param type cannot be parsed: \"%s\"", paramType);
    }
}
