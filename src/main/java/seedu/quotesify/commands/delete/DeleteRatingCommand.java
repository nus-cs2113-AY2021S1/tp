package seedu.quotesify.commands.delete;

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
 * Represents the DeleteRating command.
 */
public class DeleteRatingCommand extends DeleteCommand {

    /**
     * Constructor for the DeleteRating command.
     *
     * @param arguments Input by the user.
     */
    public DeleteRatingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DeleteRating command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        deleteRating(ratings, ui);
    }

    private void deleteRating(RatingList ratings, TextUi ui) {
        try {
            RatingParser.checkUserInput(information);
            String bookIndex = information.trim();
            Book bookToDeleteRating = RatingParser.checkBookExists(bookIndex);

            bookToDeleteRating.setRating(RatingParser.UNRATED);
            String title = bookToDeleteRating.getTitle();
            String author = bookToDeleteRating.getAuthor().getName();

            for (Rating rating : ratings.getList()) {
                if (rating.getTitle().equals(title) && rating.getAuthor().equals(author)) {
                    ratings.delete(ratings.getList().indexOf(rating));
                    ui.printDeleteRating(title, author);
                    return;
                }
            }
            quotesifyLogger.log(Level.INFO, "book has not been rated before");
            throw new QuotesifyException(ERROR_RATING_DO_NOT_EXIST);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
