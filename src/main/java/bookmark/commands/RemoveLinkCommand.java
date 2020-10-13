package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class RemoveLinkCommand extends BookmarkCommand {
    private int linkNumber;
    private int categoryNumber;

    public RemoveLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        linkNumber = Integer.parseInt(line.substring(3));
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        categories.get(categoryNumber - 1).removeLink(linkNumber);
        ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
    }
}
