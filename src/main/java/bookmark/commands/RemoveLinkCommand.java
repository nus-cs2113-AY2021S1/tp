package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkLinkException;
import exceptions.InvalidEmptyLinkException;

import java.util.ArrayList;

public class RemoveLinkCommand extends BookmarkCommand {
    public static final int RM_LENGTH = 2;
    private int linkNumber;
    private int categoryNumber;
    private String line;

    public RemoveLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        try {
            if (categoryNumber == 0) {
                ui.printChooseCategoryMessage();
                assert categoryNumber == 0 : "Choose Category message is called when category number is chosen";
            } else if (line.length() <= RM_LENGTH) {
                throw new InvalidEmptyLinkException();
            } else {
                line = line.substring(RM_LENGTH);
                assert line.length() > 0 : "Link should not be empty";
                linkNumber = evaluateLinkNumber(categories);
                categories.get(categoryNumber - 1).removeLink(linkNumber);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
            }
        } catch (InvalidEmptyLinkException e) {
            ui.showEmptyLinkError();
        } catch (InvalidBookmarkLinkException e) {
            ui.showInvalidLinkError();
        } catch (NumberFormatException e) {
            System.out.println("Enter a number");
        }
    }

    private int evaluateLinkNumber(ArrayList<BookmarkCategory> categories)
            throws NumberFormatException, InvalidBookmarkLinkException {
        line = line.trim();
        int linkNum = Integer.parseInt(line);
        if (linkNum == 0 || linkNum > categories.get(categoryNumber - 1).getLinks().size()) {
            throw new InvalidBookmarkLinkException();
        }
        return linkNum;
    }

    //Todo empty number
    // Todo throw exceptions for invalid input, not a number
    //Todo throw exceptions for input number = 0 or input number > size
}
