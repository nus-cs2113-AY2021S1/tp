package seedu.duke.commands;

import seedu.duke.category.CategoryList;
import seedu.duke.exception.QuotesifyException;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.QuoteList;
import seedu.duke.quote.QuoteParser;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.rating.RatingParser;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ListCommand extends Command {
    private String type;
    private String information;

    public ListCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_CATEGORY:
            CategoryList categoryList = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            listCategories(categoryList, ui);
            break;
        case TAG_RATING:
            RatingList ratingList = (RatingList) listManager.getList(ListManager.RATING_LIST);
            listRatings(ratingList, ui);
            break;
        case TAG_QUOTE:
            QuoteList quoteListList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
            listQuotes(quoteListList, ui);
        default:
        }
    }

    private void listQuotes(QuoteList quoteList, TextUi ui) {
        if ((information.isEmpty())) {
            listAllQuotes(quoteList, ui);
        } else if (information.contains(Command.FLAG_AUTHOR) && information.contains(Command.FLAG_REFERENCE)) {
            try {
                information = information.substring(1);
                HashMap<String, String> referenceAndAuthorName = QuoteParser.parseReferenceAndAuthor(information);
                String reference = referenceAndAuthorName.get(Command.REFERENCE_KEYWORD);
                String authorName = referenceAndAuthorName.get(Command.AUTHORNAME_KEYWORD);
                listQuotesByReferenceAndAuthor(quoteList, reference, authorName, ui);
            } catch (QuotesifyException e) {
                System.out.println(e.getMessage());
            }
        } else if (information.contains(Command.FLAG_AUTHOR)) {
            String authorName = QuoteParser.parseListWithAuthor(information);
            listQuotesByAuthor(quoteList, authorName, ui);
        } else {
            String reference = QuoteParser.parseListWithReference(information);
            listQuotesByReference(quoteList, reference, ui);
        }
    }

    private void listQuotesByReferenceAndAuthor(QuoteList quoteList, String reference, String authorName, TextUi ui) {
        ui.printAllQuotesByReferenceAndAuthor(quoteList, reference, authorName);
    }

    private void listAllQuotes(QuoteList quoteList, TextUi ui) {
        ui.printAllQuotes(quoteList);
    }

    private void listQuotesByAuthor(QuoteList quoteList, String authorName, TextUi ui) {
        ui.printAllQuotesByAuthor(quoteList, authorName);
    }

    private void listQuotesByReference(QuoteList quoteList, String reference, TextUi ui) {
        ui.printAllQuotesByReference(quoteList, reference);
    }

    private void listRatings(RatingList ratingList, TextUi ui) {
        ArrayList<Rating> ratings = ratingList.getList();
        ratings.sort(Comparator.comparing(Rating::getRating));
        Collections.reverse(ratings);
        if (information.equals("")) {
            listAllRatings(ratingList, ui);
        } else {
            listSpecifiedRating(ratingList, ui);
        }
    }

    private void listAllRatings(RatingList ratingList, TextUi ui) {
        ui.printAllRatings(ratingList);
    }

    private void listSpecifiedRating(RatingList ratings, TextUi ui) {
        int ratingToList = RatingParser.checkFormatOfRatingValue(information);
        if (RatingParser.checkRangeOfRatingValue(ratingToList)) {
            ui.printSpecifiedRating(ratings, ratingToList);
        }
    }

    private void listCategories(CategoryList categoryList, TextUi ui) {
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listCategory(categoryList, ui);
        }
    }

    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    private void listCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printCategory(categoryList.getCategoryByName(information));
        } catch (NullPointerException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
