package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Timetable;

public class FindBookmarkCommand extends Command {
    public static final String FIND_KW = "find";
    private final String description;

    /**
     * Constructs a new FindBookmarkCommand instance and gets the module and description to be searched.
     * If only one term is entered (term meaning string without a space), method asserts
     * this to be the module and inserts an empty description.
     *
     * @param command The command input by the user.
     * @throws DukeException if the input is unknown.
     */
    public FindBookmarkCommand(String command) throws DukeException {
        String details = command.substring(FIND_KW.length());
        if (details.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_COMMAND, FIND_KW);
        }
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        description = details.trim();
    }

    /**
     * Prints the bookmark with the matching keyword.
     *  @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @param bookmarkStorage The storage for saving and loading bookmarks.
     * @param slotStorage The storage for saving and loading slots.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) {
        String message = bookmarks.findBookmarks(description);
        ui.print(message);
    }
}
