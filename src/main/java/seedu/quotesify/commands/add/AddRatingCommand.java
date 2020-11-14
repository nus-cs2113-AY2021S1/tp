package seedu.quotesify.commands.add;

import seedu.quotesify.book.Book;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the AddRating Command.
 */
public class AddRatingCommand extends AddCommand {

    /**
     * Constructor for the AddRating command.
     *
     * @param arguments Input by the user.
     */
    public AddRatingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the AddRating command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        addRating(ratings, ui);
    }

    private void addRating(RatingList ratings, TextUi ui) {
        try {
            RatingParser.checkUserInput(information);
            String[] ratingDetails = information.split(" ", 2);
            String ratingValue = ratingDetails[0].trim();
            String bookNumber = ratingDetails[1].trim();
            int ratingScore = RatingParser.checkValidityOfRatingScore(ratingValue);
            Book bookToRate = RatingParser.checkBookExists(bookNumber);
            boolean isRated = isRated(bookToRate);

            if (!isRated) {
                bookToRate.setRating(ratingScore);
                ratings.add(new Rating(bookToRate, ratingScore));
                String title = bookToRate.getTitle();
                String author = bookToRate.getAuthor().getName();
                ui.printAddRating(ratingScore, title, author);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            quotesifyLogger.log(Level.INFO, "invalid format provided");
            ui.printErrorMessage(RatingParser.ERROR_INVALID_FORMAT_RATING);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private boolean isRated(Book bookToRate) throws QuotesifyException {
        if (bookToRate != null && bookToRate.getRating() != RatingParser.UNRATED) {
            quotesifyLogger.log(Level.INFO, "book has been rated before");
            throw new QuotesifyException(ERROR_RATING_EXIST);
        }
        return false;
    }
}
