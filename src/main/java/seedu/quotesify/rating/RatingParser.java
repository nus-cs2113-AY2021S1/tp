package seedu.quotesify.rating;

import seedu.quotesify.commands.AddCommand;

import java.util.logging.Level;

public class RatingParser {

    public static final int RATING_ONE = 1;
    public static final int RATING_FIVE = 5;
    public static final String ERROR_INVALID_RATING_SCORE = "That score is out of our range my friend";
    public static final String ERROR_INVALID_FORMAT_RATING = "Sorry I don't understand you";

    public static int checkValidityOfRatingScore(String rating) {
        int ratingScore = 0;
        try {
            ratingScore = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            AddCommand.addLogger.log(Level.WARNING, "format error", e);
            System.out.println(ERROR_INVALID_FORMAT_RATING);
            return 0;
        }
        if (!(ratingScore >= RATING_ONE && ratingScore <= RATING_FIVE)) {
            AddCommand.addLogger.log(Level.INFO, "rating score out of range");
            System.out.println(ERROR_INVALID_RATING_SCORE);
            return 0;
        }
        return ratingScore;
    }
}
