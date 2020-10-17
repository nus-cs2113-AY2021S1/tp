package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.Timetable;

/**
 * Represents the user command exit the Duke program.
 */
public class ExitCommand extends Command {
    public static final String BYE_KW = "exit";

    /**
     * Constructs a new ExitCommand instance and sets isExitCommand to true.
     */
    public ExitCommand() {
        isExitCommand = true;
    }

    /**
     * Prints the exit screen before the program exits.
     *  @param bookmarks The list of bookmarks.
     * @param timetable
     * @param ui The user interface.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) {
        ui.showExitScreen();
    }
}
