package seedu.quotesify.commands.list;

import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

//@@author lunzard

/**
 * Represents the command to list all bookmarks from Quotesify's BookmarkList.
 */
public class ListBookmarkCommand extends ListCommand {
    /**
     * Constructor for the ListBookmark Command.
     *
     * @param arguments Inputs by the user.
     */
    public ListBookmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the ListBookmark Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        BookmarkList bookmarkList = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
        listBookmarks(bookmarkList, ui);
    }

    /**
     * print all bookmarks in the BookmarkList.
     *
     * @param bookmarkList BookmarkList in Quotesify.
     * @param ui Ui of the program.
     */
    private void listBookmarks(BookmarkList bookmarkList, TextUi ui) {
        ui.printAllBookmarks(bookmarkList);
    }

}
