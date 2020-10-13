package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class BackCommand extends BookmarkCommand {
    BookmarkMode currentMode;

    public BackCommand(BookmarkMode currentMode){
        this.currentMode = currentMode;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (currentMode == BookmarkMode.BOOKMARK_MAIN){
            ui.printGoodbyeMessage();
        } else {
            currentMode = BookmarkMode.BOOKMARK_MAIN;
            ui.showBookmarkCategoryList(categories);
        }
    }

    public boolean isExit(){
        if (currentMode == BookmarkMode.BOOKMARK_MAIN){
            return false;
        } else {
            return true;
        }
    }

    public BookmarkMode getCurrentMode() {
        return BookmarkMode.BOOKMARK_MAIN;
    }
}
