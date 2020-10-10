package seedu.financeit.utils;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InvalidCategoryException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ParamChecker {
    public static final String PARAM_DATE = "/date";
    public static final String PARAM_TIME = "/time";
    public static final String PARAM_INDEX = "/id";
    public static final String PARAM_DESCRIPTION = "/desc";
    public static final String PARAM_CATEGORY = "/cat";
    public static final String PARAM_AMOUNT = "/amt";
    public static final String PARAM_INC = "-i";
    public static final String PARAM_EXP = "-e";

    CommandPacket packet;

    public ParamChecker(CommandPacket packet) {
        this.packet = packet;
    }

    public LocalDateTime checkAndReturnDateTime(String paramType, String dateTimeFormat)
        throws ParseFailParamException {
        LocalDateTime dateTime = null;
        boolean parseSuccess = false;
        try {
            String rawDate = packet.getParam(paramType);
            if (rawDate.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            dateTime = InputParser.parseRawDateTime(rawDate, dateTimeFormat);
            parseSuccess = true;
        } catch (DateTimeException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Not a valid date on the Gregorian Calendar!",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (InvalidParameterException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input format is not recognised.",
                "Check your input again against the following format!",
                "Date format: YYMMDD",
                "Time format: HHMM");
        } catch (EmptyParamException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage(),
                "Enter \"commands\" to check format!");
        }
        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return dateTime;
    }

    public int checkAndReturnIndex(String paramType, ItemList list)
        throws ParseFailParamException {
        String message;
        int index = -1;
        boolean parseSuccess = false;

        if (list.getItemsSize() == 0) {
            message = "There are no items in the list.";
        } else if (list.getItemsSize() == 1) {
            message = "The only valid item index is 1.";
        } else {
            message = String.format("The range is from 1 to %d", list.getItemsSize());
        }

        try {
            index = Integer.parseInt(packet.getParam(paramType));
            if (index < -1 || index >= list.getItemsSize()) {
                throw new IndexOutOfBoundsException();
            }
            parseSuccess = true;
        } catch (IndexOutOfBoundsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Index input is out of bounds!",
                message);
        } catch (NumberFormatException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Cannot parse your input. Please enter a positive integer!",
                message);
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }

        return index - 1;
    }

    public int checkAndReturnIndex(String paramType, ArrayList list) throws ParseFailParamException {
        String message;
        int index = -1;
        boolean parseSuccess = false;

        if (list.size() == 0) {
            message = "There are no items in the list.";
        } else if (list.size() == 1) {
            message = "The only valid item index is 1.";
        } else {
            message = String.format("The range is from 1 to %d", list.size());
        }

        try {
            index = Integer.parseInt(packet.getParam(paramType));
            if (index < -1 || index >= list.size()) {
                throw new IndexOutOfBoundsException();
            }
            parseSuccess = true;
        } catch (IndexOutOfBoundsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Index input is out of bounds!",
                message);
        } catch (NumberFormatException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Cannot parse your input. Please enter a positive integer!",
                message);
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
        try {
            output = Double.parseDouble(input);
            parseSuccess = true;
        } catch (NumberFormatException | NullPointerException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Cannot parse your input. Please enter valid 2 d.p input!");
        }

        if (!parseSuccess) {
            throw new ParseFailParamException(paramType);
        }
        return output;
    }

    public String checkAndReturnCategory(String paramType) throws ParseFailParamException {
        boolean parseSuccess = false;
        String category = packet.getParam(paramType);
        try {
            if (!Constants.categoryMap.containsKey(category)) {
                throw new InvalidCategoryException(category);
            }
            parseSuccess = true;
        } catch (InvalidCategoryException exception) {
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
