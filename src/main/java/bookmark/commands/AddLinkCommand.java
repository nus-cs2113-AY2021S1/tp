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
                assert categoryNumber == 0 : "Choose Category message is called when category number is chosen";
            } else if (line.length() <= ADD_LENGTH) {
                throw new InvalidEmptyLinkException();
            } else {
                link = line.substring(ADD_LENGTH);
                assert link.length() > 0 : "Link should not be empty";
                Boolean validLink = evaluateLink();
                if (!validLink) {
                    throw new InvalidBookmarkLinkException();
                }
                categories.get(categoryNumber - 1).addLink(link);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
            }
        } catch (InvalidEmptyLinkException e) {
            ui.showEmptyLinkError();
        } catch (InvalidBookmarkLinkException e) {
            ui.showInvalidLinkError();
        }
    }

    private Boolean evaluateLink() {
        if (!link.contains("https://") && !link.contains(".") && link.contains(" ")) {
            return false;
        } else {
            assert link.contains("https://") && link.contains(".") && !link.contains(" ") : "Invalid link";
            return true;
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
