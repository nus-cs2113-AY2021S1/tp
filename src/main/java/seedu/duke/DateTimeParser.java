package seedu.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Extracts dates input by the user. Returns a LocalDate.
 * Default date format is ddMMyy for fastest date input.
 */
public abstract class DateTimeParser {
    //@@author Jingming517 - reused with minor modification.

    /**
     * Converts date of a user input from a String to LocalDate.
     *
     * @param date string of date to process.
     * @return date in LocalDate format.
     */
    public static LocalDate inputDateProcessor(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

        if (date.isEmpty()) {
            throw new Exception();
        }

        LocalDate ld = LocalDate.parse(date, formatter);
        String valid = ld.format(formatter);

        if (!valid.equals(date)) {
            throw new Exception();
        }
        return ld;

    }
    //@@author Jingming517 */

    public static LocalTime inputTimeProcessor(String time) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

        if (time.isEmpty()) {
            throw new Exception();
        }

        LocalTime lt = LocalTime.parse(time, formatter);
        String valid = lt.format(formatter);

        if (!valid.equals(time)) {
            throw new Exception();
        }
        return lt;
    }

}
