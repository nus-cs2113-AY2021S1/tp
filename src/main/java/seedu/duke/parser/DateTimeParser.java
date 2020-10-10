package seedu.duke.parser;


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
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeException e) {
            //throw new Exceptions(e.getMessage());
        }
        return null;
    }

    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            //throw new Exceptions(e.getMessage());
        }
        return null;
    }

    public static long diff(LocalDate from, LocalDate to) {
        Period period = Period.between(from, to);
        return ChronoUnit.DAYS.between(from, to);
        //System.out.println(period.getDays());
        //return period.getDays();
    }
}
