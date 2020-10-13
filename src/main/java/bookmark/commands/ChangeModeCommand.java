package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class ChangeModeCommand extends BookmarkCommand {
    private int categoryNumber;

    public ChangeModeCommand(int chosenCategory) {
        this.categoryNumber = chosenCategory;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
    }

}
