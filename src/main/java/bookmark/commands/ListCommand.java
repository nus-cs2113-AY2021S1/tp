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

    /**
     * Shows user the list of bookmarks according to the variations of list command.
     * list -a : shows all the bookmark links in every category.
     * list: shows the bookmark links in the category the user is in.
     * list -s : shows all the starred bookmark links
     * list -c : shows the list of categories available
     *
     * @param ui prints output message
     * @param categories prints category list
     */

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        String line = input.substring(LIST_LENGTH).toLowerCase().trim();
        if (line.contains("-")) {
            if (line.contains("-s")) {
                executeListStarCommand(ui, categories, line);
            } else if (line.contains("-c")) {
                executeListCatCommand(ui, categories, line);
            } else if (line.contains("-a")) {
                executeListAllCommand(ui, categories, line);
            } else {
                ui.showCorrectCommand("list");
            }
        } else {
            if (line.length() > 0) {
                ui.showCorrectCommand("list");
            } else {
                if (categoryNumber == 0) {
                    ui.showBookmarkList(categories);
                } else {
                    assert categoryNumber > 0 : "Category number cannot be accessed";
                    ui.showBookmarkLinkList(categories.get(categoryNumber - 1));
                }
                printCurrentMode(ui, categories);
            }
        }
    }

    private void printCurrentMode(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (categoryNumber == 0) {
            ui.showCurrentMode("Bookmark Main");
        } else {
            ui.showCurrentMode(categories.get(categoryNumber - 1).getName() + " category");
            assert categoryNumber > 0 : "Cannot print category name when it is not available";
        }
    }

    private void executeListCatCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, String line) {
        if (line.substring(2).length() > 0) {
            ui.showCorrectCommand("list -c");
        } else {
            ui.showBookmarkCategoryList(categories);
            printCurrentMode(ui, categories);
        }
    }

    private void executeListStarCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, String line) {
        if (line.substring(2).length() > 0) {
            ui.showCorrectCommand("list -s");
        } else {
            ui.showStarBookmarks(categories);
        }
    }

    private void executeListAllCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, String line) {
        if (line.substring(2).length() > 0) {
            ui.showCorrectCommand("list -a");
        } else {
            ui.showBookmarkList(categories);
            printCurrentMode(ui, categories);
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

}
