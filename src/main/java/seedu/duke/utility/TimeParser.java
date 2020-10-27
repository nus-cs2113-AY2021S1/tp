package seedu.duke.utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TimeParser converts user's time input into int.
 */
public class TimeParser {

    public TimeParser() {

    }

    /**
     * Converts time from string format to int.
     * @param input user's input 'xhym' or 'y', where x is the hours and y is the minutes
     * @return time in minutes as an integer if input is valid or -1 if input is invalid.
     */
    public static int parseTime(String input) {
        try {
            String[] splitHours = input.split("h");
            String[] splitMinutes = splitHours[1].split("m");
            int hours = Integer.parseInt(splitHours[0]);
            int minutes = Integer.parseInt(splitMinutes[0]);
            return hours * 60 + minutes;
        } catch (ArrayIndexOutOfBoundsException e) {
            int result = Integer.parseInt(input);
            return result;

        } catch (NumberFormatException e) {
            Ui.printInvalidFormatException();
            return -1;
        }
    }
}
