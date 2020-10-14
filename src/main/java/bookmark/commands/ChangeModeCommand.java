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
        int categoryNumberInList = categoryNumber - 1;
        System.out.println("You are now in " + categories.get(categoryNumberInList).getName() + " category");
        System.out.println("The following are your current bookmarks in this category");
        ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
        System.out.println("Add new bookmarks by using \"add <link>\"");
    }
}