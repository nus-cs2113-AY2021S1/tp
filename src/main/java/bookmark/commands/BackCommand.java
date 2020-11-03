package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import java.util.ArrayList;

public class BackCommand extends BookmarkCommand {
    private int categoryNumber;

    public BackCommand(int chosenCategory) {
        this.categoryNumber = chosenCategory;
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        if (categoryNumber == 0) {
            ui.printGoodbyeMessage();
        } else {
            ui.showBookmarkCategoryList(categories);
            assert categoryNumber > 0 : "Category number more than 0";
            categoryNumber = 0;
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}