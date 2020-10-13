package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;
import seedu.duke.Bookmark.Commands.BookmarkCommand;

import java.util.ArrayList;

public class changeModeCommand extends BookmarkCommand {
    private int chosenCategory;

    public changeModeCommand(String line){
        chosenCategory = Integer.parseInt(line.substring(3));
    }
    public boolean isExit(){
        return true;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        ui.showBookmarkLinkList(categories.get(chosenCategory-1).getLinks());
    }

    public BookmarkMode getCurrentMode(){
        switch(chosenCategory){
        case 1: return BookmarkMode.NUS;
        case 2: return BookmarkMode.ZOOM;
        default: return BookmarkMode.BOOKMARK_MAIN;
        }
    }

}
