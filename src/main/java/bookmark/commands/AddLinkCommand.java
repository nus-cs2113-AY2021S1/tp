package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkLinkException;
import exceptions.InvalidEmptyLinkException;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    public static final int ADD_LENGTH = 3;
    private String line;
    private int categoryNumber;
    private String link;

    public AddLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
        assert line.startsWith("add") : "Add link command is called when line does not start with add";
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        try {
            if (categoryNumber == 0) {
                ui.printChooseCategoryMessage();
            } else {
                assert categoryNumber > 0 : "Category number is not chosen";
                evaluateCategoryNumber();
                link = line.substring(ADD_LENGTH).trim();
                evaluateLink();
                categories.get(categoryNumber - 1).addLink(link);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
            }
        } catch (InvalidEmptyLinkException e) {
            ui.showEmptyLinkError();
        } catch (InvalidBookmarkLinkException e) {
            ui.showInvalidLinkError();
        }
    }

    private void evaluateLink() throws InvalidBookmarkLinkException {
        if (!link.contains("https://") || !link.contains(".") || link.contains(" ")) {
            throw new InvalidBookmarkLinkException();
        }
        assert link.contains("https://") && link.contains(".") && !link.contains(" ") : "Invalid link";
    }

    private void evaluateCategoryNumber() throws InvalidEmptyLinkException {
        if (line.length() <= ADD_LENGTH) {
            throw new InvalidEmptyLinkException();
        }
        assert line.length() > 0 : "Link should not be empty";

    }


    public int getCategoryNumber() {
        return categoryNumber;
    }
}
