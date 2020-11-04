package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ListCommand extends BookmarkCommand {
    public static final int LIST_LENGTH = 4;
    public static final int STAR_LENGTH = 4;
    public static final int CAT_LENGTH = 3;
    private int categoryNumber;
    private String input;

    public ListCommand(String command, int categoryNumber) {
        this.input = command.trim();
        this.categoryNumber = categoryNumber;
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        String line = input.substring(LIST_LENGTH).trim();
        if (line.length() > 0) {
            if (line.contains("star")) {
                executeListStarCommand(ui, categories, line);
            } else if (line.contains("cat")) {
                executeListCatCommand(ui, categories, line);
            } else {
                ui.showCorrectCommand("list");
            }
        } else {
            ui.showBookmarkList(categories);
            printCurrentMode(ui, categories);
        }
    }

    private void printCurrentMode(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (categoryNumber == 0) {
            ui.showCurrentMode("Bookmark Main");
        } else {
            ui.showCurrentMode(categories.get(categoryNumber - 1).getName() + " category");
            assert categoryNumber > 0 : "print category name when it is not available";
        }
    }

    private void executeListCatCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, String line) {
        if (line.substring(CAT_LENGTH).length() > 0) {
            ui.showCorrectCommand("list cat");
        } else {
            ui.showBookmarkCategoryList(categories);
            printCurrentMode(ui, categories);
        }
    }

    private void executeListStarCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, String line) {
        if (line.substring(STAR_LENGTH).length() > 0) {
            ui.showCorrectCommand("list star");
        } else {
            ui.showStarBookmarks(categories);
            printCurrentMode(ui, categories);
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

}
