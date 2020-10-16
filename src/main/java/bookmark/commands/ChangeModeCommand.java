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
            getChosenCategory(line, categories);
            int categoryNumberInList = categoryNumber - 1;
            System.out.println("You are now in " + categories.get(categoryNumberInList).getName() + " category");
            System.out.println("The following are your current bookmarks in this category");
            ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
            System.out.println("Add new bookmarks by using \"add <link>\"");
        } catch (InvalidEmptyLinkException e) {
            System.out.println("Empty category number");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
        } catch (InvalidBookmarkLinkException e){
            System.out.println("Invalid category number");
        }
    }

    private void getChosenCategory(String line, ArrayList<BookmarkCategory> categories)
            throws InvalidBookmarkLinkException, InvalidEmptyLinkException, NumberFormatException {
        if (line.trim().length() <= RM_LENGTH){
            throw new InvalidEmptyLinkException();
        }
        int category = Integer.parseInt(line.substring(RM_LENGTH).trim());
        if (category == 0 || category > categories.size()){
            throw new InvalidBookmarkLinkException();
        } else if (categoryNumber == category){
            System.out.println("Already in chosen Category");
        } else {
            categoryNumber = category;
        }

    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    //Todo throw exceptions for invalid input
    //Todo throw exceptions for category number = 0 or category number > size
    //Todo throw exceptions saying that it is already in the mode
}