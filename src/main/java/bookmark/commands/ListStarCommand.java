package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListStarCommand extends BookmarkCommand {
    private int categoryNumber;

    public ListStarCommand(int categoryNumber) {
        this.categoryNumber = categoryNumber;
        assert categoryNumber >= 0 : "Missing category number";
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        ui.showStarBookmarks(categories);
    }
}