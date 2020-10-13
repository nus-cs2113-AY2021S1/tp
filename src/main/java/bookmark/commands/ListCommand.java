package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListCommand extends BookmarkCommand {
    private int categoryNumber;

    public ListCommand(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        ui.showBookmarkList(categories);
    }

}
