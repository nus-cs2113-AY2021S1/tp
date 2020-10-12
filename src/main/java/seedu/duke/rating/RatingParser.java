package seedu.duke.rating;

import seedu.duke.commands.AddCommand;

import java.util.logging.Level;

public class RatingParser {

    public static final int RATING_ONE = 1;
    public static final int RATING_FIVE = 5;
    public static final String ERROR_INVALID_RATING_SCORE = "That score is out of our range my friend";
    public static final String ERROR_INVALID_FORMAT_RATING = "Sorry I don't understand you";

    public static int checkFormatOfRatingValue(String rating) {
        int ratingToList = 0;
        try {
            ratingToList = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            AddCommand.addLogger.log(Level.WARNING, "format error", e);
            System.out.println(ERROR_INVALID_FORMAT_RATING);
        }
        return ratingToList;
    }

    public static boolean checkRangeOfRatingValue(int rating) {
        if (!(rating >= RATING_ONE && rating <= RATING_FIVE)) {
            AddCommand.addLogger.log(Level.INFO, "rating score out of range");
            System.out.println(ERROR_INVALID_RATING_SCORE);
            return false;
        }
        return true;
    }
}
