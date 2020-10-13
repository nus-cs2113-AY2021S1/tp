package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class changeModeCommand extends BookmarkCommand {
    private int categoryNumber;

    public changeModeCommand(int chosenCategory){
        this.categoryNumber = chosenCategory;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        ui.showBookmarkLinkList(categories.get(categoryNumber-1).getLinks());
    }

    public int getCategoryNumber(){
        return categoryNumber;
    }

}
