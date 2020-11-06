package seedu.commons;

import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.task.Priority;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String generatePadStringWithCharAndLength(char pad, int length) {
        if (length == 0) {
            return "";
        } else {
            return String.format("%" + length + "s", "").replace(' ', pad);
        }
    }

    public static String limitStringWithDots(String string, int limit) {
        // Could be changed to -4 to get an extra space
        if (limit <= 0) {
            return "";
        } else if (limit < 3) {
            return string.substring(0, limit);
        }
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
    }

    public static void putsIntoArray(String string, char[] arr, int start) {
        string.getChars(0, string.length(), arr, start);
    }

    public static void putsIntoArrayWithCentralise(String string, char[] arr, int start, int end) {
        // assert within range
        assert (end - start) > string.length();
        int dstBegin = start + (end - start - string.length()) / 2;
        string.getChars(0, string.length(), arr, dstBegin);
    }

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

    public static String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        } else {
            return " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    public static String timeToString(LocalTime time) {
        if (time == null) {
            return "";
        } else {
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    public static String priorityToString(Priority priority) {
        return " " + priority.toString();
    }
}
