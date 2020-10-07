package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;

public class ListCommand extends Command {
    public static final String LIST_KW = "list";

    /**
     * Constructs a new ListCommand instance.
     */
    public ListCommand() {

    }

    /**
     * Prints all the details of the tasks in the list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */

    private String getMessage(BookmarkList bookmarks) {
        String message = "\tHere are the bookmarks in your list:\n";
        for (int i = 0; i < bookmarks.getSize(); i++) {
            message = message + "\t" + (i + 1) + "." + bookmarks.getBookmark(i).getBookmarkAsString() + "\n";
        }
        return message;
    }

    @Override
    public void execute(BookmarkList bookmarks, Ui ui, Storage storage) throws DukeException {
        String message = getMessage(bookmarks);
        ui.printPublic(message);
    }
}
