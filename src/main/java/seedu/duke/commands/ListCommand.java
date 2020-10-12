package seedu.duke.commands;

import seedu.duke.bookmark.BookmarkList;
import seedu.duke.book.BookList;
import seedu.duke.category.CategoryList;
import seedu.duke.exception.QuotesifyException;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.QuoteList;
import seedu.duke.quote.QuoteParser;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.rating.RatingParser;
import seedu.duke.todo.ToDoList;
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
        assert details.length == 2;
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui) {
        switch (type) {
        case TAG_CATEGORY:
            CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
            listCategories(categoryList, ui);
            break;
        case TAG_RATING:
            RatingList ratingList = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            listRatings(ratingList, ui);
            break;
        case TAG_TODO:
            ToDoList toDoList = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
            listToDos(toDoList,ui);
            break;
        case TAG_BOOKMARK:
            BookmarkList bookmarkList = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
            listBookmarks(bookmarkList, ui);
            break;
        case TAG_QUOTE:
            QuoteList quoteListList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            listQuotes(quoteListList, ui);
            break;
        case TAG_BOOK:
            BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            listBooks(bookList, ui);
            break;
        default:
        }
    }

    private void listBooks(BookList bookList, TextUi ui) {
        try {
            if (information.isEmpty()) {
                listAllBooks(bookList, ui);
            } else if (information.contains(FLAG_AUTHOR)) {
                listBooksByAuthor(bookList, ui);
            } else {
                throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_AUTHOR_NAME);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void listAllBooks(BookList bookList, TextUi ui) throws QuotesifyException {
        if (bookList.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_IN_LIST);
        }
        ui.printAllBooks(bookList);
    }

    private void listBooksByAuthor(BookList bookList, TextUi ui) throws QuotesifyException, IndexOutOfBoundsException {
        String authorName = information.substring(4);
        BookList filteredBooks = bookList.filterByAuthor(authorName);
        if (filteredBooks.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_BY_AUTHOR);
        }
        ui.printBooksByAuthor(filteredBooks, authorName);
    }

    private void listQuotes(QuoteList quoteList, TextUi ui) {
        try {
            if ((information.isEmpty())) {
                listAllQuotes(quoteList, ui);
            } else if (information.contains(Command.FLAG_AUTHOR) && information.contains(Command.FLAG_REFERENCE)) {
                information = information.substring(1);
                HashMap<String, String> referenceAndAuthorName = QuoteParser.parseReferenceAndAuthor(information);
                String reference = referenceAndAuthorName.get(Command.REFERENCE_KEYWORD);
                String authorName = referenceAndAuthorName.get(Command.AUTHORNAME_KEYWORD);
                listQuotesByReferenceAndAuthor(quoteList, reference, authorName, ui);
            } else if (information.contains(Command.FLAG_AUTHOR)) {
                String authorName = QuoteParser.parseListWithAuthor(information);
                listQuotesByAuthor(quoteList, authorName, ui);
            } else if (information.contains(Command.FLAG_REFERENCE)) {
                String reference = QuoteParser.parseListWithReference(information);
                listQuotesByReference(quoteList, reference, ui);
            } else {
                throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
            }
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
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
        if (information.isEmpty()) {
            listAllRatings(ratingList, ui);
        } else {
            listSpecifiedRating(ratingList, ui);
        }
    }

    private void listAllRatings(RatingList ratingList, TextUi ui) {
        ui.printAllRatings(ratingList);
    }

    private void listSpecifiedRating(RatingList ratings, TextUi ui) {
        assert information.isEmpty() : "Rating details should not be empty";
        int ratingToList = RatingParser.checkFormatOfRatingValue(information);
        if (ratingToList == 0) {
            return;
        }
        if (RatingParser.checkRangeOfRatingValue(ratingToList)) {
            ui.printSpecifiedRating(ratings, ratingToList);
        }
    }

    private void listCategories(CategoryList categoryList, TextUi ui) {
        categoryList.updateListsInCategory();
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listAllInCategory(categoryList, ui);
        }
    }

    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    private void listAllInCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printAllInCategory(categoryList.getCategoryByName(information));
        } catch (NullPointerException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void listToDos(ToDoList toDoList, TextUi ui) {
        ui.printAllToDos(toDoList);
    }

    private void listBookmarks(BookmarkList bookmarkList, TextUi ui) {
        ui.printAllBookmarks(bookmarkList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
