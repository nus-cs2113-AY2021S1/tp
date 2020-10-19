package seedu.quotesify.commands;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class FindCommand extends Command {

    private String type;
    private String information;

    public FindCommand(String arguments) {
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
            findRating(ratings, ui);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            findQuote(quotes, ui);
            break;
        default:
            ui.printListOfFindCommands();
            break;
        }
        Storage.save();
    }

    private void findQuote(QuoteList quotes, TextUi ui) {
        try {
            String keyword = information.trim();
            if (!keyword.isEmpty()) {
                String resultList = quotes.findQuoteByKeyword(quotes, keyword);
                if (!resultList.isEmpty()) {
                    ui.printFindQuoteSuccess(resultList);
                } else {
                    ui.printFindQuoteFail();
                }
            } else {
                throw new QuotesifyException(ERROR_FIND_KEYWORD_MISSING);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(ERROR_FIND_KEYWORD_MISSING);
        }
    }

    private void findRating(RatingList ratings, TextUi ui) {
        String ratingToFind = information.trim();
        if (ratingToFind.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_BOOK_TITLE);
            return;
        }
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(ratingToFind)) {
                ui.printFoundRating(rating, ratingToFind);
                return;
            }
        }
        System.out.println(ERROR_RATING_NOT_FOUND);
    }

    public boolean isExit() {
        return false;
    }
}
