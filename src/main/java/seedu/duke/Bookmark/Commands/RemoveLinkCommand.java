package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;
import seedu.duke.Bookmark.Commands.BookmarkCommand;

import java.util.ArrayList;

public class RemoveLinkCommand extends BookmarkCommand {
    private int linkNumber;
    private BookmarkMode currentMode;

    public RemoveLinkCommand(BookmarkMode currentMode, String line){
        this.currentMode = currentMode;
        linkNumber = Integer.parseInt(line.substring(3));
    }

    public boolean isExit(){
        return true;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        int categoryNumber = evaluateCategoryNumber();
        categories.get(categoryNumber).removeLink(linkNumber);
        ui.showBookmarkLinkList(categories.get(categoryNumber).getLinks());
    }

    public BookmarkMode getCurrentMode(){
        return currentMode;
    }

    private int evaluateCategoryNumber(){
        if (currentMode == BookmarkMode.ZOOM){
            return 1;
        } else if (currentMode == BookmarkMode.NUS){
            return 0;
        }
        return 0;
    }
}
