package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkLinkException;
import exceptions.InvalidEmptyLinkException;

import java.util.ArrayList;

public class ChangeModeCommand extends BookmarkCommand {
    private String line;
    public static final int RM_LENGTH = 2;
    private int categoryNumber;

    public ChangeModeCommand(String line, int categoryNumber) {
        this.line = line;
        this.categoryNumber = categoryNumber;
        assert line.startsWith("bm") : "change mode command is called when line does not start with cd";
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        try {
            int category = getChosenCategory(line, categories);
            if (category == categoryNumber) {
                ui.showAlreadyInModeMessage();
            } else {
                categoryNumber = category;
                int categoryNumberInList = categoryNumber - 1;
                ui.showModeChangeMessage(categories, categoryNumberInList);
                assert category != categoryNumber : "Mode is changed when it is already in the mode";
            }
        } catch (InvalidEmptyLinkException e) {
            ui.showEmptyLinkError();
        } catch (NumberFormatException e) {
            ui.showInvalidNumberError();
        } catch (InvalidBookmarkLinkException e) {
            ui.showInvalidLinkError();
        }
    }

    private int getChosenCategory(String line, ArrayList<BookmarkCategory> categories)
            throws InvalidBookmarkLinkException, InvalidEmptyLinkException, NumberFormatException {
        if (line.trim().length() <= RM_LENGTH) {
            throw new InvalidEmptyLinkException();
        }
        int category = Integer.parseInt(line.substring(RM_LENGTH).trim());
        if (category == 0 || category > categories.size()) {
            throw new InvalidBookmarkLinkException();
        }
        assert category > 0 && category < categories.size() : "category number out of bounds";
        return category;

    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

}