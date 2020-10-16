package seedu.quotesify.commands;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.ui.TextUi;

public class EditCommand extends Command {

    private String type;
    private String information;

    public EditCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui) {
        switch (type) {
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            editRating(ratings, ui);
            break;
        default:
        }
    }

    private void editRating(RatingList ratings, TextUi ui) {

        String[] ratingDetails;
        String titleToUpdate;

        try {
            ratingDetails = information.split(" ", 2);
            titleToUpdate = ratingDetails[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERROR_RATING_MISSING_BOOK_TITLE_OR_RATING_SCORE);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        boolean isValid = ((ratingScore != 0) && isRated(ratings, titleToUpdate));

        if (isValid) {
            Rating ratingToUpdate = null;
            for (Rating rating : ratings.getList()) {
                if (rating.getTitleOfRatedBook().equals(titleToUpdate)) {
                    ratingToUpdate = rating;
                }
            }
            assert ratingToUpdate != null;
            ratingToUpdate.setRating(ratingScore);
            ui.printEditRatingToBook(ratingScore, titleToUpdate);
        }
    }

    private boolean isRated(RatingList ratings, String titleToUpdate) {
        boolean isRated = false;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(titleToUpdate)) {
                isRated = true;
                break;
            }
        }

        if (!isRated) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return false;
        }
        return true;
    }

    public boolean isExit() {
        return false;
    }
}
