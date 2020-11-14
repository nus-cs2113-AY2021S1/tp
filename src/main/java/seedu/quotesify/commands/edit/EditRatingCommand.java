package seedu.quotesify.commands.edit;

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
 * Represents the EditRating Command.
 */
public class EditRatingCommand extends EditCommand {

    /**
     * Constructor for the EditRating command.
     *
     * @param arguments Input by the user.
     */
    public EditRatingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the EditRating command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        editRating(ratings, ui);
    }

    private void editRating(RatingList ratings, TextUi ui) {
        try {
            RatingParser.checkUserInput(information);
            String[] ratingDetails = information.split(" ", 2);
            String ratingValue = ratingDetails[0].trim();
            String bookNumber = ratingDetails[1].trim();
            int ratingScore = RatingParser.checkValidityOfRatingScore(ratingValue);
            Book bookToRate = RatingParser.checkBookExists(bookNumber);

            String title = bookToRate.getTitle();
            String author = bookToRate.getAuthor().getName();
            Rating existingRating = isRated(title, author, ratings);

            bookToRate.setRating(ratingScore);
            existingRating.setRating(ratingScore);
            ui.printEditRating(ratingScore, title, author);
        } catch (ArrayIndexOutOfBoundsException e) {
            quotesifyLogger.log(Level.INFO, "invalid format provided");
            ui.printErrorMessage(RatingParser.ERROR_INVALID_FORMAT_RATING);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private Rating isRated(String title, String author, RatingList ratings) throws QuotesifyException {
        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().equals(title) && rating.getAuthor().equals(author)) {
                return rating;
            }
        }
        quotesifyLogger.log(Level.INFO, "book has not been rated before");
        throw new QuotesifyException(ERROR_RATING_DO_NOT_EXIST);
    }
}
