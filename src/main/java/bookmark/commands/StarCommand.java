package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.EmptyBookmarkException;
import exceptions.InvalidBookmarkException;

import java.util.ArrayList;

public class StarCommand extends BookmarkCommand {
    public static final int STAR_LENGTH = 4;
    private int chosenCategory;
    private String line;
    private int starLinkNumber;

    public StarCommand(String line, int chosenCategory) {
        this.line = line.trim();
        this.chosenCategory = chosenCategory;
    }

    /**
     * Mark existing BookmarkLink as star or unstar existing BookmarkLink.
     *
     * @param ui prints output message
     * @param categories star link in array list
     * @param storage update starred link in file
     * @throws EmptyBookmarkException if link number is empty
     * @throws NumberFormatException if number is not the input
     * @throws IndexOutOfBoundsException if number exceed large number
     * @throws InvalidBookmarkException if bookmark link number is invalid.
     */

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (chosenCategory == 0) {
                ui.printChooseCategoryMessage();
            } else if (line.length() <= STAR_LENGTH) {
                throw new EmptyBookmarkException();
            } else {
                assert chosenCategory > 0 : "Category number is not chosen";
                evaluateStarNumber(categories);
                categories.get(chosenCategory - 1).markLinkAsStar(starLinkNumber - 1);
                ui.showBookmarkLinkList(categories.get(chosenCategory - 1));
                ui.showStarBookmarks(categories);
                storage.saveLinksToFile(categories);
            }
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError("Star Number");
        } catch (InvalidBookmarkException e) {
            ui.showInvalidError("Star Number");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showInvalidNumberError();
        }
    }

    private void evaluateStarNumber(ArrayList<BookmarkCategory> categories)
            throws NumberFormatException, InvalidBookmarkException {
        line = line.substring(STAR_LENGTH).trim();
        int catNum = Integer.parseInt(line);
        if (catNum == 0 || catNum > categories.get(chosenCategory - 1).getLinks().size()) {
            throw new InvalidBookmarkException();
        }
        starLinkNumber = catNum;
    }

    public int getCategoryNumber() {
        return chosenCategory;
    }
}
