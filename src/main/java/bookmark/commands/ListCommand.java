package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListCommand extends BookmarkCommand {
    public static final int LIST_LENGTH = 4;
    private int categoryNumber;
    private String input;

    public ListCommand(String command, int categoryNumber) {
        this.input = command.trim();
        this.categoryNumber = categoryNumber;
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        if (input.substring(LIST_LENGTH).length() > 0) {
            ui.showCorrectCommand("list");
        }
        if (categoryNumber == 0) {
            ui.showBookmarkList(categories, "Bookmark Main");
        } else {
            ui.showBookmarkList(categories, categories.get(categoryNumber - 1).getName());
            assert categoryNumber > 0 : "print category name when it is not available";
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

}
