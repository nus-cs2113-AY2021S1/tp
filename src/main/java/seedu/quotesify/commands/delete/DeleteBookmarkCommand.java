package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DeleteBookmarkCommand extends DeleteCommand {
    public DeleteBookmarkCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
        deleteBookmarkByIndex(bookmarks,information.trim(),ui);
    }

    private void deleteBookmark(BookList books, BookmarkList bookmarks, String titleName, TextUi ui) {
        Book targetBook = books.findByTitle(titleName);
        if (targetBook != null) {
            removeBookmarkFromBook(targetBook, bookmarks, ui);
        } else {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        }
    }

    private void deleteBookmarkByIndex(BookmarkList bookmarks, String bookmarkNumber, TextUi ui) {
        int bookmarkNum = convertBookmarkNumberToInt(bookmarkNumber, ui);
        if (bookmarkNum <= bookmarks.getSize() && bookmarkNum > 0) {
            Bookmark targetBookmark = bookmarks.findByIndex(bookmarkNum - 1);
            bookmarks.delete(bookmarkNum - 1);
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

    private int convertBookmarkNumberToInt(String numberString, TextUi ui) {
        int numInt = -1;
        try {
            numInt = Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOKMARK_NUM);
        }

        return numInt;
    }
}
