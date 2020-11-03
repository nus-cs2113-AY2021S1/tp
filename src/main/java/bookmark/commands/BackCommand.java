package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
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

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        if (input.substring(BACK_LENGTH).length() > 0) {
            System.out.println("I think you meant back...");
            System.out.println("Executing back command...");
        }
        if (categoryNumber == 0) {
            ui.printGoodbyeMessage();
        } else {
            ui.showBookmarkCategoryList(categories);
            categoryNumber = 0;
            assert categoryNumber == 0 : "Category number not updated";
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}