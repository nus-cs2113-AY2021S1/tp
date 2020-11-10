package seedu.notus.util;

import seedu.notus.data.exception.SystemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@@author brandonywl
/**
 * Represents a DateTimeManager. Manages the date and time info.
 */
public class DateTimeManager {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Parses a user input into the relevant datetime format specified in the manager.

     * @param input User input to be parsed
     * @return DateTime representation of the user input
     * @throws SystemException Occurs when the format of the input is wrong and in the specified format.
     */
    public static LocalDateTime dateTimeParser(String input) throws SystemException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_INVALID_TIMING_FORMAT);
        }
        return dateTime;
    }

    public static String getMonthName(LocalDate date) {
        return date.getMonth().name();
    }
}
