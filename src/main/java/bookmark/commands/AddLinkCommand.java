package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    public static final int ADD_LENGTH = 4;
    private String link;
    private int categoryNumber;

    public AddLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        link = line.substring(ADD_LENGTH);
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (categoryNumber == 0) {
            ui.printChooseCategoryMessage();
        } else {
            categories.get(categoryNumber - 1).addLink(link);
            ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
        }
    }
}
