package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListCommand extends BookmarkCommand {
    private int categoryNumber;
    public ListCommand(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        ui.showBookmarkList(categories);
    }

    public int getCategoryNumber(){
        return categoryNumber;
    }

}
