package seedu.duke.utility;

import java.time.LocalDateTime;

/**
 * TimeParser converts user's time input into int.
 */
public class TimeParser {

    public TimeParser() {

    }

    /**
     * Converts time from string format to int.
     * @param input user's input
     * @return time in minutes as an integer
     */
    public int parseTime(String input) {

        String[] splitHours = input.split("h");
        String[] splitMinutes = splitHours[1].split("m");
        /*
        When the user input does not specify hours and minutes
         */
        try {
            if (splitHours.equals(splitMinutes)) {
                int result = Integer.parseInt(input);
                return result;

            } else {
                int hours = Integer.parseInt(splitHours[0]);
                int minutes = Integer.parseInt(splitMinutes[0]);
                return hours * 60 + minutes;
            }

        } catch (NumberFormatException e) {
            Ui.printBadInputException();
            return -1;
        }
    }
}
