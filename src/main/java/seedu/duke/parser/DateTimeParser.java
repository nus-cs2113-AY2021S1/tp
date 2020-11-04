package seedu.duke.parser;

import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.TimeErrorException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final String[] dateFormatStrings = {"yyyy/M/d", "yyyy-M-d", "d/M/yy",
        "d/M/yyyy", "d-M-yy", "d-M-yyyy"};
    private static final String[] timeFormatStrings = {"h:m a", "hhmm a", "H:m", "HHmm"};

    /**
     * Parses the given date through the acceptable formats.
     * Acceptable date format is dd/mm/yy, yyyy/mm/dd, slashes can be replace with dashes.
     *
     * @param date given to parse.
     * @return LocalDate object containing given date.
     * @throws DateErrorException if date format is not accepted.
     */
    public static LocalDate dateParser(String date) throws DateErrorException {
        assert date != null : "date cannot be null";
        int i = 0;
        while (i < dateFormatStrings.length) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatStrings[i]);
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeException e) {
                i++;
            }
        }
        throw new DateErrorException("Something is wrong with the date!");
    }

    /**
     * Parses the given time through the acceptable formats.
     * Acceptable time format is both 12 hour and 24 hour, omitting colon requires the format hhmm.
     *
     * @param time given to parse.
     * @return LocalTime object containing the given time.
     * @throws TimeErrorException if time format is not accepted.
     */
    public static LocalTime timeParser(String time) throws TimeErrorException {
        assert time != null : "time cannot be null";
        time = time.toUpperCase();
        int i = 0;
        while (i < timeFormatStrings.length) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormatStrings[i]);
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeException e) {
                i++;
            }
        }
        throw new TimeErrorException("Something is wrong with the time!");
    }
}
