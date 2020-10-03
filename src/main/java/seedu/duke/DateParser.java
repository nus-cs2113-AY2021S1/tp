package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extracts dates input by the user. Returns a LocalDate.
 * Default date format is ddMMyy for fastest date input.
 */
public abstract class DateParser {
    /**
     * Converts date of a user input from a String to LocalDate.
     *
     * @param date string of date to process.
     * @return date in LocalDate format.
     * @@author Jingming517 - reused with minor modification.
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
    /** @@author Jingming517 */

}
