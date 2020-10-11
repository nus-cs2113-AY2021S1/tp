package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.category.CategoryParser;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

import javax.print.attribute.standard.NumberUp;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String type;
    private String information;

    public DeleteCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui) {
        switch (type) {
        case TAG_CATEGORY:
            CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
            deleteCategoryFromBookOrQuote(categories, ui);
            break;
        case TAG_BOOK:
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            deleteBook(books, ui);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            String bookTitle = information.trim();
            deleteRating(ratings, ui, bookTitle);
            break;
        case TAG_TODO:
            ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
            int index = computeToDoIndex(information.trim());
            deleteToDo(toDos, index, ui);
            break;
        case TAG_BOOKMARK:
            BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
            String title = information.trim();
            deleteBookmark(bookList, bookmarks, title, ui);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            deleteQuote(quotes, ui, information);
            break;
        default:
        }
    }

    private void deleteQuote(QuoteList quotes, TextUi ui, String information) {
        try {
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            Quote quoteToBeDeleted = quotes.getQuote(quoteNumber);
            quotes.delete(quoteNumber);
            ui.printDeleteQuote(quoteToBeDeleted.getQuote());
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private void deleteRating(RatingList ratings, TextUi ui, String bookTitle) {
        Rating ratingToBeDeleted = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(bookTitle)) {
                ratingToBeDeleted = rating;
                break;
            }
        }
        if (ratingToBeDeleted == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return;
        }
        ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
        ui.printDeleteRating(bookTitle);
    }

    private void deleteBook(BookList books, TextUi ui) {
        String[] titleAndAuthor = information.split(FLAG_AUTHOR);
        String bookTitle = titleAndAuthor[0].trim();

        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        Rating ratingToBeDeleted;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(bookTitle)) {
                ratingToBeDeleted = rating;
                ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
                break;
            }
        }

        try {
            ArrayList<Book> filteredBooks = books.find(bookTitle, titleAndAuthor[1].trim());
            books.deleteByBook(filteredBooks.get(0));
            ui.printDeleteBook(filteredBooks.get(0));
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        }
    }

    private void deleteCategoryFromBookOrQuote(CategoryList categories, TextUi ui) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        executeParameters(categories, parameters, ui);
    }

    private void executeParameters(CategoryList categories, String[] parameters, TextUi ui) {
        try {
            String categoryName = parameters[0];
            String bookTitle = parameters[1];
            int quoteNum = Integer.parseInt(parameters[2]) - 1;
            Category category = categories.getCategoryByName(categoryName);

            if (quoteNum == -2 && bookTitle == null) {
                ui.printErrorMessage(ERROR_MISSING_BOOK_OR_QUOTE);
                return;
            }

            if (deleteCategoryFromBook(category, bookTitle)) {
                ui.printRemoveCategoryFromBook(bookTitle, categoryName);
            }

            if (deleteCategoryFromQuote(category, quoteNum)) {
                QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
                ArrayList<Quote> quotes = quoteList.getList();
                ui.printRemoveCategoryFromQuote(quotes.get(quoteNum).getQuote(), categoryName);
            }
            // ui.printCategorySize(category);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean deleteCategoryFromBook(Category category, String bookTitle) {
        if (bookTitle == null || category == null) {
            return false;
        }

        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            Book book = bookList.findByTitle(bookTitle);
            if (book.getCategory().equals(category)) {
                book.setCategory(null);
            }
        } catch (NullPointerException e) {
            System.out.println(ERROR_NO_BOOK_FOUND);
            return false;
        }
        return true;
    }

    private boolean deleteCategoryFromQuote(Category category, int quoteNum) {
        if (quoteNum < 0 || category == null) {
            return false;
        }

        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();

        try {
            Quote quote = quotes.get(quoteNum);
            if (quote.getCategory().equals(category)) {
                quote.setCategory(null);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
            return false;
        }
        return true;
    }

    private void deleteToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo toDoToBeDeleted = toDos.find(index);
        if (toDoToBeDeleted != null) {
            toDos.delete(index);
            ui.printDeleteToDo(toDoToBeDeleted);
        } else {
            System.out.println(ERROR_TODO_NOT_FOUND);
        }
    }

    private int computeToDoIndex(String information) {
        int index = 0;
        try {
            index = Integer.parseInt(information);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_TODO_NUM);
        }

        return index;
    }

    private void deleteBookmark(BookList books, BookmarkList bookmarks, String titleName, TextUi ui) {
        Book targetBook = books.findByTitle(titleName);
        if (targetBook != null) {
            removeBookmarkFromBook(targetBook, bookmarks, ui);
        }
    }

    private void removeBookmarkFromBook(Book targetBook, BookmarkList bookmarks, TextUi ui) {
        Bookmark bookmarkToBeDeleted = bookmarks.find(targetBook);

        if (bookmarkToBeDeleted != null) {
            bookmarks.delete(bookmarkToBeDeleted);
            ui.printDeleteBookmark(bookmarkToBeDeleted);
        } else {
            System.out.println(ERROR_BOOKMARK_NOT_FOUND);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
