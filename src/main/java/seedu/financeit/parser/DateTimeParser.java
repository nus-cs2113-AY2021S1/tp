package seedu.financeit.parser;

import seedu.financeit.utils.RegexMatcher;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeParser {
    public static LocalDate parseLocalDate(String input) throws InvalidParameterException,
        NullPointerException, DateTimeException {
        if (RegexMatcher.alphabetMatcher(input).find()) {
            throw new InvalidParameterException();
        } else {
            String formattedString = getFormatStringForLocalDateParse(input);
            return LocalDate.parse(formattedString);
        }
    }

    public static LocalTime parseLocalTime(String input) {
        if (RegexMatcher.alphabetMatcher(input).find()) {
            throw new InvalidParameterException();
        } else {
            String formattedString = getFormatStringForLocalTimeParse(input);
            return LocalTime.parse(formattedString);
        }
    }

    public static String getFormatStringForLocalDateParse(String input) {
        final String partition = "-";
        String[] output = new String[3];
        if (input.matches("[0-9]{6}")) {
            // If input date format is XXXXXX, replace with XX,XX,XX. Eg: 200404
            input = input.substring(0,2) + "," + input.substring(2, 4) + "," + input.substring(4);
        } else if (input.matches("[0-9]{8}")) {
            // If input date format is XXXXXXXX, replace with XXXX,XX,XX. Eg: 20200404
            input = input.substring(0,4) + "," + input.substring(4, 6) + "," + input.substring(6);
        }

        input = input.replaceAll("[\\D]+", "c");
        String[] tokens = input.split("c");
        for (int i = 0; i < output.length; i++) {
            if (i > tokens.length - 1) {
                // If subsequent pair of digits is undefined by simplified input, set to 01 (Jan, 1).
                output[i] = "01";
            } else if (tokens[i].length() < 2) {
                // If token is 1 or 0 digits long, fill the remaining space with 0 such that a 2 digit number is formed.
                output[i] = "0" + tokens[i];
            } else if (i == 0 && tokens[i].matches("[0-9]{2}")) {
                // If year given is YY
                output[i] = "20" + tokens[i].substring(0,2);
            } else {
                output[i] = tokens[i];
            }
        }
        return String.join(partition, output);
    }

    public static String getFormatStringForLocalTimeParse(String input) {
        final String partition = ":";
        String[] output = new String[3];
        if (input.matches("[0-9]{4}")) {
            // If input time format is XXXX, replace with XX:XX:00.
            input = input.substring(0,2) + "," + input.substring(2) + ",00";
        }
        input = input.replaceAll("[\\D]+", "c");
        String[] tokens = input.split("c");
        for (int i = 0; i < output.length; i++) {
            if (i > tokens.length - 1) {
                // If subsequent pair of digits is undefined by simplified input, set to 01 (Jan, 1).
                output[i] = "01";
            } else if (tokens[i].length() < 2) {
                // If token is 1 or 0 digits long, fill the remaining space with 0 such that a 2 digit number is formed.
                output[i] = "0" + tokens[i];
            } else {
                output[i] = tokens[i];
            }
        }
        return String.join(partition, output);
    }
}
