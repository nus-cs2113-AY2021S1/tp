package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.SlotList;

public class DeleteBookmarkCommand extends Command {
    public static final String DEL_KW = "delete";
    private int index;


    /**
     * Constructs a new ExitCommand instance and sets isExitCommand to true.
     */

    public DeleteBookmarkCommand(String command) throws DukeException {
        String details = command.substring(DEL_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }
    }


    /**
     * Prints the exit screen before the program exits.
     *
     * @param bookmarks The list of bookmarks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */
    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui, Storage storage) throws DukeException {
        try {
            Bookmark bookmark = bookmarks.getBookmark(index);
            bookmarks.deleteBookmark(bookmarks.getBookmark(index));
            ui.printPublic(getMessage(bookmark));
            //storage.save(taskList.getData());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }
    }

    private String getMessage(Bookmark bookmark) {
        String message = "\tNI've deleted this bookmark!:\n"
                + "\t  [" + bookmark.getModule() + "] " + bookmark.getDescription() + " " + bookmark.getUrl() + "\n";
        return message;
    }
}
