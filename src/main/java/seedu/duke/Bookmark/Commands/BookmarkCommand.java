package seedu.duke.Bookmark.Commands;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkMode;
import seedu.duke.Bookmark.BookmarkUi;

import java.util.ArrayList;

public abstract class BookmarkCommand {
    public abstract void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories);
    public abstract boolean isExit();
    public abstract BookmarkMode getCurrentMode();

}
