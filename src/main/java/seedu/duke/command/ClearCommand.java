package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand extends Command {
    public static final String CLEAR_KW = "clear";

    /**
     * Constructs a new ShowBookmarkCommand instance.
     */
    public ClearCommand() {
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) {
        ui.clearScreen();
    }
}
