package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.slot.Timetable;

public class ShowBookmarkCommand extends Command {
    public static final String LIST_KW = "show";

    /**
     * Constructs a new ShowBookmarkCommand instance.
     */
    public ShowBookmarkCommand() {

    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) {
        ui.print(bookmarks.showBookmarks());
    }
}
