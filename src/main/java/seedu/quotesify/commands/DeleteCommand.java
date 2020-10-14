package seedu.quotesify.commands;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

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
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
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

        // clear bookmarks before delete the entire book.
        BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
        clearBookmark(books,bookmarks, bookTitle, ui);

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
            if (titleAndAuthor[1].isEmpty()) {
                throw new QuotesifyException(ERROR_NO_AUTHOR_NAME);
            }
            ArrayList<Book> filteredBooks = books.find(bookTitle, titleAndAuthor[1].trim());
            books.deleteByBook(filteredBooks.get(0));
            ui.printDeleteBook(filteredBooks.get(0));
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void deleteCategoryFromBookOrQuote(CategoryList categories, TextUi ui) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        if (CategoryParser.isValidParameters(parameters)) {
            executeParameters(categories, parameters, ui);
        }
    }

    private void executeParameters(CategoryList categories, String[] parameters, TextUi ui) {
        try {
            String categoryName = parameters[0];
            assert !categoryName.isEmpty() : "category name should not be empty";

            Category category = categories.getCategoryByName(categoryName);

            String bookTitle = parameters[1];
            String quoteNum = parameters[2];

            deleteCategoryFromBook(category, bookTitle, ui);
            deleteCategoryFromQuote(category, quoteNum, ui);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void deleteCategoryFromBook(Category category, String bookTitle, TextUi ui) {
        // ignore this action if user did not provide book title
        if (bookTitle.isEmpty()) {
            return;
        }

        BookList bookList = category.getBookList();
        try {
            Book book = bookList.findByTitle(bookTitle);
            if (book.getCategory().equals(category)) {
                book.setCategory(null);
            }
            ui.printRemoveCategoryFromBook(bookTitle, category.getCategoryName());
        } catch (NullPointerException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND + "\b tagged as [" + category.getCategoryName() + "]!");
        }
    }

    private void deleteCategoryFromQuote(Category category, String index, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (index.isEmpty()) {
            return;
        }

        QuoteList quoteList = category.getQuoteList();
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            int quoteNum = Integer.parseInt(index) - 1;
            Quote quote = quotes.get(quoteNum);
            if (quote.getCategory().equals(category)) {
                quote.setCategory(null);
            }
            ui.printRemoveCategoryFromQuote(quote.getQuote(), category.getCategoryName());
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_QUOTE_FOUND + "\b tagged as [" + category.getCategoryName() + "]!");
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        }
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
        } else {
            System.out.println(ERROR_NO_BOOK_FOUND);
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

    private void clearBookmark(BookList books, BookmarkList bookmarks, String titleName, TextUi ui) {
        Book targetBook = books.findByTitle(titleName);
        if (targetBook != null) {
            clearBookmarkFromDeletedBook(targetBook, bookmarks, ui);
        }
    }

    private void clearBookmarkFromDeletedBook(Book targetBook, BookmarkList bookmarks, TextUi ui) {
        Bookmark bookmarkToBeDeleted = bookmarks.find(targetBook);

        if (bookmarkToBeDeleted != null) {
            bookmarks.delete(bookmarkToBeDeleted);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
