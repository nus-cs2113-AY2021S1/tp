package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditRatingCommand extends EditCommand {

    public EditRatingCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        editRating(ratings, ui);
    }

    private void editRating(RatingList ratings, TextUi ui) {
        if (information.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_INPUTS);
            return;
        }

        String[] ratingDetails;
        String title;
        String author;
        try {
            ratingDetails = information.split(" ", 2);
            String[] titleAndAuthor = ratingDetails[1].split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }
        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        Rating existingRating = checkIfRatingExists(ratings, title, author);
        boolean isValid = ((ratingScore != 0) && (existingRating != null));

        if (isValid) {
            existingRating.setRating(ratingScore);
            existingRating.getRatedBook().setRating(ratingScore);
            ui.printEditRatingToBook(ratingScore, title, author);
        }
    }

    private Rating checkIfRatingExists(RatingList ratings, String title, String author) {
        Rating existingRating = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                existingRating = rating;
                break;
            }
        }

        if (existingRating == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return null;
        }
        return existingRating;
    }
}
