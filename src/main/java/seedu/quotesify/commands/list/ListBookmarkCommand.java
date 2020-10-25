package seedu.quotesify.commands.list;

import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class ListBookmarkCommand extends ListCommand{
    public ListBookmarkCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookmarkList bookmarkList = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
        listBookmarks(bookmarkList, ui);
    }

    private void listBookmarks(BookmarkList bookmarkList, TextUi ui) {
        ui.printAllBookmarks(bookmarkList);
    }

}
