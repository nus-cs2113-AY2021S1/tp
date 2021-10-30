package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkList;
import bookmark.BookmarkUi;
import exceptions.ExistingBookmarkException;
import exceptions.InvalidBookmarkException;
import exceptions.EmptyBookmarkException;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    public static final int ADD_LENGTH = 3;
    private String line;
    private int categoryNumber;
    private String link;
    private String title;

    public AddLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
        assert line.toLowerCase().startsWith("add") : "Add link command is called when line does not start with add";
        assert categoryNumber >= 0 : "Missing category number";
    }

    /**
     * Adds new BookmarkLink to ArrayList after evaluating category link.
     *
     * @param ui prints output message
     * @param categories add link to array list
     * @param storage store new link to file
     * @throws EmptyBookmarkException if category name is empty
     * @throws ExistingBookmarkException if bookmark category already exist
     * @throws InvalidBookmarkException if bookmark link is invalid.
     */
    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (categoryNumber == 0) {
                ui.printChooseCategoryMessage();
            } else {
                assert categoryNumber > 0 : "Category number is not chosen";
                evaluateLink();
                checkLink(categories);
                categories.get(categoryNumber - 1).addLink(link,title);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1));
                storage.saveLinksToFile(categories);
            }
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError("Link / title");
        } catch (InvalidBookmarkException e) {
            ui.showInvalidError("Link");
        } catch (ExistingBookmarkException e) {
            ui.showExistingBookmarkError("link");
        }
    }

    private void evaluateLink() throws EmptyBookmarkException, InvalidBookmarkException {
        if (line.length() <= ADD_LENGTH) {
            throw new EmptyBookmarkException();
        }
        assert line.length() > 0 : "Link should not be empty";
        link = line.substring(ADD_LENGTH).trim();
        if (link.contains(" t->")) {
            String[] array = link.split(" t->");
            if (array.length < 2) {
                throw new EmptyBookmarkException();
            }
            link = array[0].trim();
            title = array[1].trim();
        } else {
            title = null;
        }
        if (!link.contains(".") || link.contains(" ") || link.contains("|")) {
            throw new InvalidBookmarkException();
        }
        assert link.contains(".") && !link.contains(" ") : "Invalid link";
    }

    private void checkLink(ArrayList<BookmarkCategory> categories) throws ExistingBookmarkException {
        for (BookmarkList existingLink : categories.get(categoryNumber - 1).getLinks()) {
            if (link.equals(existingLink.getLink())) {
                throw new ExistingBookmarkException();
            }
        }
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
