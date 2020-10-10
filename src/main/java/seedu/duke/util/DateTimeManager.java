package seedu.duke.util;

import seedu.duke.data.exception.SystemException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a DateTimeManager. Manages the date and time info.
 */
public class DateTimeManager {
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATETIMEFORMAT);

    /**
     * Parses a user input into the relevant datetime format specified in the manager.
     * @param input User input to be parsed
     * @return DateTime representation of the user input
     * @throws SystemException Occurs when the format of the input is wrong and not the same as the specified formatter type.
     */
    public static LocalDateTime dateTimeParser(String input) throws SystemException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_WRONG_TIMING);
        }
        return dateTime;
    }
}
