package seedu.quotesify.commands.edit;

import seedu.quotesify.book.Book;
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

    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        editRating(ratings, ui);
    }

    private void editRating(RatingList ratings, TextUi ui) {

        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            return;
        }

        String[] ratingDetails;
        String ratingValue;
        String bookNumber;

        try {
            ratingDetails = information.split(" ", 2);
            ratingValue = ratingDetails[0].trim();
            bookNumber = ratingDetails[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingValue);
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        String title = bookToRate.getTitle();
        String author = bookToRate.getAuthor().getName();
        Rating existingRating = isRated(title, author, ratings);
        boolean isValid = ((ratingScore != RatingParser.INVALID_RATING) && (existingRating != null));
        if (isValid) {
            bookToRate.setRating(ratingScore);
            existingRating.setRating(ratingScore);
            ui.printEditRating(ratingScore, title, author);
        }
    }

    private Rating isRated(String title, String author, RatingList ratings) {
        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().equals(title) && rating.getAuthor().equals(author)) {
                return rating;
            }
        }
        System.out.println(ERROR_RATING_DO_NOT_EXIST);
        return null;
    }
}
