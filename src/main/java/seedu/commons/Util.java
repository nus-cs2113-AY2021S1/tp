package seedu.commons;

import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.task.Priority;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides common utility functions for other classes.
 */
public class Util {

    /**
     * Generates a pad string with character and length.
     * @param pad character that needs to be duplicate.
     * @param length number of the characters required.
     * @return String of pad characters of length.
     */
    public static String generatePadStringWithCharAndLength(char pad, int length) {
        if (length == 0) {
            return "";
        } else {
            return String.format("%" + length + "s", "").replace(' ', pad);
        }
    }

    /**
     * Adds three dots to the back of a string if the length of the string exceeds.
     * @param string to be processed.
     * @param limit length limit.
     * @return processed string.
     */
    public static String limitStringWithDots(String string, int limit) {
        if (limit <= 0) {
            return "";
        } else if (limit < 3) {
            return string.substring(0, limit);
        }
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
    }

    /**
     * Copy a string into a character array.
     * @param string source.
     * @param arr destination.
     * @param start position of the array.
     */
    public static void putsIntoArray(String string, char[] arr, int start) {
        string.getChars(0, string.length(), arr, start);
    }

    /**
     * Copy a string into a character array with centralise.
     * @param string source.
     * @param arr destination.
     * @param start position of the array.
     * @param end position of the array.
     */
    public static void putsIntoArrayWithCentralise(String string, char[] arr, int start, int end) {
        // assert within range
        // assert (end - start + 1) >= string.length();
        int dstBegin = start + (end - start - string.length()) / 2;
        string.getChars(0, string.length(), arr, dstBegin);
    }

    /**
     * Convert a string representation of date with format DD-MM-YYYY to a LocalDate object.
     * @param dateString string representation of date.
     * @return LocalDate object represented by dateString.
     * @throws InvalidDatetimeException when date values are not in range.
     */
    public static LocalDate dateStringToDate(String dateString) throws InvalidDatetimeException {
        if (dateString == null) {
            return LocalDate.now();
        }
        String[] dateParts = dateString.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }

    }

    /**
     * Convert a string representation of time with format HHMM to a LocalTime object.
     * @param timeString string representation of time.
     * @return LocalTime object represented by timeString.
     * @throws InvalidDatetimeException when time values are not in range.
     */
    public static LocalTime timeStringToTime(String timeString) throws InvalidDatetimeException {
        if (timeString == null) {
            return null;
        }
        int time = Integer.parseInt(timeString);
        int hour = time / 100;
        int minute = time % 100;

        try {
            return LocalTime.of(hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }
    }

    /**
     * Convert a string representation of priority to a Priority object.
     * @param priorityString string representation of priority.
     * @return Priority object represented by string.
     * @throws InvalidPriorityException when priority value is not in range.
     */
    public static Priority priorityStringToPriority(String priorityString) throws InvalidPriorityException {
        if (priorityString == null) {
            return Priority.LOW;
        }
        Priority priority;
        switch (priorityString) {
        case "1":
            priority = Priority.LOW;
            break;
        case "2":
            priority = Priority.MEDIUM;
            break;
        case "3":
            priority = Priority.HIGH;
            break;
        default:
            throw new InvalidPriorityException();
        }
        return priority;
    }

    /**
     * Converts date object into its string representation.
     * @param date LocalDate object to be converted.
     * @return String representation of the date.
     */
    public static String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        } else {
            return " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    /**
     * Converts time object into its string representation.
     * @param time LocalTime object to be converted.
     * @return String representation of the time.
     */
    public static String timeToString(LocalTime time) {
        if (time == null) {
            return "";
        } else {
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    /**
     * Converts priority object into its string representation.
     * @param priority Priority object to be converted.
     * @return String representation of the priority.
     */
    public static String priorityToString(Priority priority) {
        return " " + priority.toString();
    }
}
