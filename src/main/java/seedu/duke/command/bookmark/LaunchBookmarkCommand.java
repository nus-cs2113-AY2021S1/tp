package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Timetable;

import java.util.ArrayList;
import java.util.Arrays;

public class LaunchBookmarkCommand extends Command {
    public static final String LAUNCH_KW = "launch";
    private int index;
    private String description;
    private int launchTypeFlag;

    /**
     * Constructs a new LaunchBookmarkCommand instance and gets the index of the bookmark to launch.
     * Determines whether the launch command was input with an integer or string, and sets launchTypeFlag accordingly.
     * Integer (launchTypeFlag = 1)
     * Strings (launchTypeFlag = 2)
     *
     * @param command The command input by the user.
     * @throws DukeException if the input is unknown or if the bookmark number is invalid.
     */
    public LaunchBookmarkCommand(String command) throws DukeException {
        String details = command.substring(LAUNCH_KW.length());
        if (details.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_COMMAND, LAUNCH_KW);
        }
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
            launchTypeFlag = 1; // (flag to launch bookmark at specified index)
        } catch (NumberFormatException e) {
            if (details.trim().contains(" ")) {
                throw new DukeException(DukeExceptionType.INVALID_COMMAND_FORMAT);
            }
            description = details.trim();
            launchTypeFlag = 2; // (flag to launch bookmarks with matching module and description)
        }
    }

    /**
     * Launches the bookmark based on the launchTypeFlag previously determined in LaunchBookmarkCommand initialization.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @param bookmarkStorage The storage for saving and loading bookmarks.
     * @param slotStorage The storage for saving and loading slots.
     * @throws DukeException if the bookmark number is invalid or if there is an error launching the URL.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        if (launchTypeFlag == 1) { // Launch based on index
            try {
                Bookmark bookmark = bookmarks.getBookmark(index);
                String message = "Nice! I've launched this bookmark!:\n" + bookmark.launch();
                ui.print(message);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(DukeExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS, ""
                        + bookmarks.getBookmarkList().size());
            }
            return;
        } else if (launchTypeFlag == 2) { // Launch based on matching module and description
            String message = bookmarks.launchBookmarks(description);
            ui.print(message);
        }
    }
}
