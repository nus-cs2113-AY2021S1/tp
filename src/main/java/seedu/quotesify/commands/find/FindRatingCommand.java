package seedu.quotesify.commands.find;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the FindRating Command.
 */
public class FindRatingCommand extends FindCommand {

    /**
     * Constructor for the FindRating command.
     *
     * @param arguments Input by the user.
     */
    public FindRatingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the FindRating command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        try {
            findRating(ratings, ui);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void findRating(RatingList ratings, TextUi ui) throws QuotesifyException {
        try {
            RatingParser.checkUserInput(information);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            return;
        }

        assert information != null : "keyword should not be null";
        String keyword = information.toLowerCase().trim();

        boolean isFound = false;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().toLowerCase().contains(keyword)) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            quotesifyLogger.log(Level.INFO, "ratings not found");
            throw new QuotesifyException(ERROR_RATING_NOT_FOUND);
        }
        ui.printFoundRating(ratings, keyword);
    }
}
