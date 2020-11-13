package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.EmptyBookmarkException;
import exceptions.ExistingBookmarkException;
import exceptions.InvalidBookmarkException;

import java.util.ArrayList;

public class BackCommand extends BookmarkCommand {
    public static final int BACK_LENGTH = 4;
    private int categoryNumber;
    private String input;

    public BackCommand(String command, int chosenCategory) {
        this.input = command.trim();
        this.categoryNumber = chosenCategory;
        assert categoryNumber >= 0 : "Missing category number";
    }

    /**
     * Goes back to the previous mode vy changing category number.
     *
     * @param ui prints output message
     * @param categories prints category list
     */
    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        if (input.substring(BACK_LENGTH).length() > 0) {
            ui.showCorrectCommand("back");
        } else {
            if (categoryNumber == 0) {
                ui.printGoodbyeMessage();
            } else {
                ui.showCurrentMode("Bookmark Main");
                ui.showBookmarkCategoryList(categories);
                assert categoryNumber > 0 : "Category number more than 0";
                categoryNumber = 0;
            }
        }

    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}