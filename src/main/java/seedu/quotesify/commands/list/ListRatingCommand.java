package seedu.quotesify.commands.list;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;

/**
 * Represents the ListRating Command.
 */
public class ListRatingCommand extends ListCommand {

    /**
     * Constructor for the ListRating command.
     *
     * @param arguments Input by the user.
     */
    public ListRatingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the ListRating command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        listRatings(ratings, ui);
    }

    private void listRatings(RatingList ratingList, TextUi ui) {
        ArrayList<Rating> ratings = ratingList.getList();
        ratings.sort(Comparator.comparing(Rating::getRating));
        Collections.reverse(ratings);
        if (information.isEmpty()) {
            listAllRatings(ratingList, ui);
        } else {
            try {
                listSpecifiedRating(ratingList, ui);
            } catch (QuotesifyException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    private void listAllRatings(RatingList ratingList, TextUi ui) {
        ui.printAllRatings(ratingList);
    }

    private void listSpecifiedRating(RatingList ratings, TextUi ui) throws QuotesifyException {
        assert !information.isEmpty() : "Rating details should not be empty";
        int ratingToPrint = 0;
        try {
            ratingToPrint = RatingParser.checkValidityOfRatingScore(information);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            return;
        }

        boolean isFound = false;
        for (Rating rating : ratings.getList()) {
            if (rating.getRating() == ratingToPrint) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            quotesifyLogger.log(Level.INFO, "ratings not found");
            throw new QuotesifyException(ERROR_LIST_SPECIFIED_RATING_NOT_FOUND);
        }
        ui.printSpecifiedRating(ratings, ratingToPrint);
    }
}
