package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkException;
import exceptions.EmptyBookmarkException;

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
        } catch (EmptyBookmarkException e) {
            ui.showEmptyLinkError();
        } catch (InvalidBookmarkException e) {
            ui.showInvalidLinkError();
        }
    }

    private void evaluateLink() throws InvalidBookmarkException {
        if (!link.contains("https://") || !link.contains(".") || link.contains(" ")) {
            throw new InvalidBookmarkException();
        }
        assert link.contains("https://") && link.contains(".") && !link.contains(" ") : "Invalid link";
    }

    private void evaluateCategoryNumber() throws EmptyBookmarkException {
        if (line.length() <= ADD_LENGTH) {
            throw new EmptyBookmarkException();
        }
        assert line.length() > 0 : "Link should not be empty";

    }


    public int getCategoryNumber() {
        return categoryNumber;
    }
}
