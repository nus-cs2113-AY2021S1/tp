package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class BackCommand extends BookmarkCommand {
    private int categoryNumber;

    public BackCommand(int categoryNumber){
        this.categoryNumber = categoryNumber;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (categoryNumber == 0){
            ui.printGoodbyeMessage();
        } else {
            categoryNumber = 0;
            ui.showBookmarkCategoryList(categories);
        }
    }

    public int getCategoryNumber(){
        return categoryNumber;
    }
}
