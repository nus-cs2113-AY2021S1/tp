package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.ExistingBookmarkException;
import exceptions.InvalidBookmarkException;
import exceptions.EmptyBookmarkException;

import java.util.ArrayList;

public class ChangeModeCommand extends BookmarkCommand {
    private String line;
    public static final int RM_LENGTH = 2;
    private int categoryNumber;

    public ChangeModeCommand(String line, int categoryNumber) {
        this.line = line.trim();
        this.categoryNumber = categoryNumber;
        assert line.startsWith("bm") : "change mode command is called when line does not start with cd";
        assert categoryNumber >= 0 : "Missing category number";
    }

    /**
     * Change mode from bookmark main to each bookmark category.
     *
     * @param ui prints output message.
     * @param categories prints list of links in category chosen.
     */
    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage bookmarkStorage) {
        try {
            int category = getChosenCategory(categories);
            if (category == categoryNumber) {
                ui.showAlreadyInModeMessage(categories,categoryNumber);
                assert category == categoryNumber : "studyit.Mode does not change when it is already in the mode";
            } else {
                categoryNumber = category;
                int categoryNumberInList = categoryNumber - 1;
                ui.showModeChangeMessage(categories, categoryNumberInList);
                assert category > 0 : "Category number incorrect";
            }
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError("Category Number");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showInvalidNumberError();
        } catch (InvalidBookmarkException e) {
            ui.showInvalidError("Category Number");
        }
    }

    private int getChosenCategory(ArrayList<BookmarkCategory> categories)
            throws InvalidBookmarkException, EmptyBookmarkException, NumberFormatException {
        if (line.length() <= RM_LENGTH) {
            throw new EmptyBookmarkException();
        }
        int category = Integer.parseInt(line.substring(RM_LENGTH).trim());
        if (category == 0 || category > categories.size()) {
            throw new InvalidBookmarkException();
        }
        return category;

    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

}