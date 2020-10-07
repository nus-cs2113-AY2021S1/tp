package seedu.duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final String[] dateformatStrings = {"yyyy/M/d", "yyyy-M-d", "d/M/yy",
            "d/M/yyyy", "d-M-yy", "d-M-yyyy"};
    private static final String[] timeformatStrings = {"h:m a", "hhmm a", "H:m", "HHmm"};

    /**
     * Parses the given date through the acceptable formats.
     * Acceptable date format is dd/mm/yy, yyyy/mm/dd, slashes can be replace with dashes.
     *
     * @param date given to parse.
     * @return LocalDate object containing given date.
     * @throws Exception if date format is not accepted.
     */
    public static LocalDate dateParser(String date) throws Exception {
        for (String formatString : dateformatStrings) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeException ignored) {
            }
        }
        throw new Exception("Something is wrong with the date!");
    }

    /**
     * Parses the given time through the acceptable formats.
     * Acceptable time format is both 12 hour and 24 hour, omitting colon requires the format hhmm.
     *
     * @param time given to parse.
     * @return LocalTime object containing the given time.
     * @throws Exception if time format is not accepted.
     */
    public static LocalTime timeParser(String time) throws Exception {
        time = time.toUpperCase();
        for (String formatString : timeformatStrings) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeException ignored) {
            }
        }
        throw new Exception("Something is wrong with the time!");
    }
}
