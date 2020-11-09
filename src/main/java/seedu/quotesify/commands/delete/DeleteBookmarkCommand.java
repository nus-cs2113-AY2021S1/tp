package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

//@@author lunzard

/**
 * Represents the command to delete bookmarks from Quotesify's BookmarkList.
 */
public class DeleteBookmarkCommand extends DeleteCommand {
    /**
     * Constructor for the DeleteBookmark Command.
     *
     * @param arguments Inputs by the user.
     */
    public DeleteBookmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DeleteBookmark Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
        deleteBookmarkByIndex(bookmarks,information.trim(),ui);
    }

    /**
     * Delete bookmark of selected index from the BookmarkList.
     *
     * @param bookmarks BookmarkList in Quotesify.
     * @param bookmarkNumber  Bookmark Number of the selected bookmark in the BookmarkList.
     * @param ui Ui of the program.
     */
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

    /**
     * Convert the bookmark number from String to int.
     * Negative bookmark number will be saved as -1 for further handling.
     *
     * @param numberString Bookmark number in String.
     * @return Bookmark number in int.
     */
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
