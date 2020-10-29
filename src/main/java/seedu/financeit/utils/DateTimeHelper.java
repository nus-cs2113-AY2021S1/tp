package seedu.financeit.utils;

public class DateTimeHelper {

    /**
     * Returns day as an ordinal number e.g. 1st, 23rd
     *
     * @param day integer
     * @return Formatted string
     */
    public static String dayAsOrdinal(int day) {
        int lastDigit = day % 10;
        int firstDigit = day / 10;

        if (firstDigit == 1) {
            return day + "th";
        }

        switch (lastDigit) {
        case 1: return day + "st";
        case 2: return day + "nd";
        case 3: return day + "rd";
        default: return day + "th";
        }
    }
}
