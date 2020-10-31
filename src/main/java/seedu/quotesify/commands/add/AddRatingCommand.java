package seedu.quotesify.commands.add;

import seedu.quotesify.book.Book;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;
import seedu.quotesify.ui.UiMessage;

import java.util.logging.Level;

public class AddRatingCommand extends AddCommand {

    public AddRatingCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        addRating(ratings, ui);
    }

    private void addRating(RatingList ratings, TextUi ui) {
        System.out.println(UiMessage.DIVIDER_LINE);
        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            quotesifyLogger.log(Level.INFO, "user input is missing");
            System.out.println(UiMessage.DIVIDER_LINE);
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
            quotesifyLogger.log(Level.INFO, "invalid format provided");
            System.out.println(UiMessage.DIVIDER_LINE);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingValue);
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        boolean isRated = isRated(bookToRate);
        boolean isValid = (ratingScore != RatingParser.INVALID_RATING) && (bookToRate != null) && (!isRated);
        if (isValid) {
            bookToRate.setRating(ratingScore);
            ratings.add(new Rating(bookToRate, ratingScore));
            String title = bookToRate.getTitle();
            String author = bookToRate.getAuthor().getName();
            ui.printAddRating(ratingScore, title, author);
        }
        System.out.println(UiMessage.DIVIDER_LINE);
    }

    private boolean isRated(Book bookToRate) {
        if (bookToRate != null && bookToRate.getRating() != RatingParser.UNRATED) {
            quotesifyLogger.log(Level.INFO, "book has been rated before");
            System.out.println(ERROR_RATING_EXIST);
            return true;
        }
        return false;
    }
}
