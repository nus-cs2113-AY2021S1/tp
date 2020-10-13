package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;
import seedu.duke.Bookmark.Commands.BookmarkCommand;

import java.util.ArrayList;

public class ListCommand extends BookmarkCommand {
    private BookmarkMode currentMode;

    public ListCommand(BookmarkMode currentMode){
        this.currentMode = currentMode;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        int categoryNumber = evaluateCategoryNumber();
        ui.showBookmarkLinkList(categories.get(categoryNumber).getLinks());
    }

    public boolean isExit(){
        return true;
    }

    private int evaluateCategoryNumber(){
        if (currentMode == BookmarkMode.ZOOM){
            return 1;
        } else if (currentMode == BookmarkMode.NUS){
            return 0;
        }
        return 0;
    }

    public BookmarkMode getCurrentMode(){
        return currentMode;
    }
}
