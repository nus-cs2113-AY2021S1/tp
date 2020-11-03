package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListStarCommand extends BookmarkCommand {
    public static final int LIST_STAR_LENGTH = 9;
    private int categoryNumber;
    private String input;

    public ListStarCommand(String command, int categoryNumber) {
        input = command.trim();
        this.categoryNumber = categoryNumber;
        assert categoryNumber >= 0 : "Missing category number";
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        if (input.substring(LIST_STAR_LENGTH).length() > 0) {
            System.out.println("I think you meant list star...");
            System.out.println("Executing list star command...");
        }
        ui.showStarBookmarks(categories);
    }
}