package seedu.duke.parser;


import seedu.duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeParser {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * Parses String into LocalDateTime object.
     *
     * @param dateTime String in the DATETIME_FORMAT
     * @return LocalDateTime object based on the String
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static LocalDate parseDate(String date) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static long diff(LocalDate from, LocalDate to) {
        Period period = Period.between(from, to);
        return ChronoUnit.DAYS.between(from, to);
    }
}
