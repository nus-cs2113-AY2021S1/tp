package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

public class DeleteCommand extends Command {
    public String type;
    public String information;
    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_CATEGORY:
            new DeleteCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new DeleteBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            deleteRating(ratings, ui);
            break;
        case TAG_TODO:
            ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
            int index = computeToDoIndex(information.trim());
            deleteToDo(toDos, index, ui);
            break;
        case TAG_BOOKMARK:
            BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
            deleteBookmarkByIndex(bookmarks,information.trim(),ui);
            break;
        case TAG_QUOTE:
            new DeleteQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            deleteQuoteReflection(quoteList, ui, information);
            break;
        default:
            ui.printListOfDeleteCommands();
            break;
        }
        storage.save();
    }

    private void deleteQuoteReflection(QuoteList quoteList, TextUi ui, String information) {
        try {
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            quoteList.deleteReflection(quoteNumber);
            ui.printDeleteQuoteReflection(quoteList.getQuote(quoteNumber).getQuote());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private void deleteRating(RatingList ratings, TextUi ui) {
        if (information.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_INPUTS);
            return;
        }

        String[] titleAndAuthor;
        String title;
        String author;
        try {
            titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        Rating ratingToBeDeleted = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                ratingToBeDeleted = rating;
                break;
            }
        }

        if (ratingToBeDeleted == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return;
        }
        ratingToBeDeleted.getRatedBook().setRating(0);
        ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
        ui.printDeleteRating(title, author);
    }

    private void deleteToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo toDoToBeDeleted = toDos.find(index);
        if (toDoToBeDeleted != null) {
            toDos.delete(index);
            ui.printDeleteToDo(toDoToBeDeleted);
        } else {
            ui.printErrorMessage(ERROR_TODO_NOT_FOUND);
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
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        }
    }

    private void deleteBookmarkByIndex(BookmarkList bookmarks, String index, TextUi ui) {
        int indexNum = convertBookmarkIndexToInt(index, ui);
        if (indexNum <= bookmarks.getSize()) {
            Bookmark targetBookmark = bookmarks.findByIndex(indexNum - 1);
            bookmarks.delete(indexNum);
            ui.printDeleteBookmark(targetBookmark);
        } else {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        }
    }

    private void removeBookmarkFromBook(Book targetBook, BookmarkList bookmarks, TextUi ui) {
        Bookmark bookmarkToBeDeleted = bookmarks.find(targetBook);

        if (bookmarkToBeDeleted != null) {
            bookmarks.delete(bookmarkToBeDeleted);
            ui.printDeleteBookmark(bookmarkToBeDeleted);
        } else {
            ui.printErrorMessage(ERROR_BOOKMARK_NOT_FOUND);
        }
    }

    private int convertBookmarkIndexToInt(String indexString, TextUi ui) {
        int index = -1;
        try {
            index = Integer.parseInt(information) - 1;
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOKMARK_NUM);
        }

        return index;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}