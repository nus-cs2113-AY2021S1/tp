package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    private String link;
    private int categoryNumber;

    public AddLinkCommand(String line, int categoryNumber){
        this.categoryNumber = categoryNumber;
        link = line.substring(4);
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        categories.get(categoryNumber-1).addLink(link);
        ui.showBookmarkLinkList(categories.get(categoryNumber-1).getLinks());
    }

    public int getCategoryNumber(){
        return categoryNumber;
    }
}
