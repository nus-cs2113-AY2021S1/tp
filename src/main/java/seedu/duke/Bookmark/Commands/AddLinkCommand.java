package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    private String link;
    private BookmarkMode currentMode;
    public AddLinkCommand(BookmarkMode currentMode, String line){
        this.currentMode = currentMode;
        link = line.substring(4);
    }
    public boolean isExit(){
        return true;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        int categoryNumber = evaluateCategoryNumber();
        categories.get(categoryNumber).addLink(link);
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
