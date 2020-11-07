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

    public static String catchDateFormat(String date) throws DukeException {
        try {
            String monthInString = date.substring(4, 6);
            int month = Integer.parseInt(monthInString.trim());
            boolean isThirtyDays = (month == 4 || month == 6 || month == 9 || month == 11);
            boolean isFebruary = (month == 2);
            String dayInString = date.substring(6, 8);
            int day = Integer.parseInt(dayInString.trim());
            if (isFebruary) {
                String yearInString = date.substring(0, 4);
                int year = Integer.parseInt(yearInString.trim());
                boolean isLeapYear = checkLeapYear(year);
                if (isLeapYear && (day > 29 && day < 32)) {
                    throw new DukeException("There are only 29 days on the February in the specified year.");
                }
                if (!isLeapYear && (day > 28 && day < 32)) {
                    throw new DukeException("There are only 28 days on the February in the specified year.");
                }

            } else if (isThirtyDays) {
                if (day > 30) {
                    throw new DukeException("There are only 30 days on the specified month.");
                }
            } else {
                if (day > 31) {
                    throw new DukeException("There are only 31 days on the specified month.");
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return date;
    }

    public static boolean checkLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 != 0) {
                return true;
            } else if (year % 400 == 0) {
                return true;
            }
        }
        return false;
    }

    public static LocalDate parseDate(String date) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException("Please indicate a valid date in this following format: YYYYMMDD.");
        }
    }

    public static long diff(LocalDate from, LocalDate to) {
        Period period = Period.between(from, to);
        return ChronoUnit.DAYS.between(from, to);
    }
}
