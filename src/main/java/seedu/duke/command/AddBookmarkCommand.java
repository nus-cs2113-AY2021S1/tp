package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;

import java.util.List;

/**
 * Represents the user command exit the Duke program.
 */
public class AddBookmarkCommand extends Command {
    public static final String ADD_KW = "add";
    public String module;
    public String description;
    public String url;

    /**
     * Constructs a new ExitCommand instance and sets isExitCommand to true.
     */

    public AddBookmarkCommand(String command) throws DukeException {
        List<String> descAndAt = Bookmark.extractModuleDescriptionAndLink(command);
        module = descAndAt.get(0);
        description = descAndAt.get(1);
        url = descAndAt.get(2);
        //Commit
    }


    /**
     * Prints the exit screen before the program exits.
     *
     * @param bookmarks The list of bookmarks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */
    @Override
    public void execute(BookmarkList bookmarks, Ui ui, Storage storage) {
        Bookmark bookmark = new Bookmark(module, description, url);
        bookmarks.addBookmark(bookmark);
        ui.printPublic("Added bookmark: " + "[" + module + "] "
                + description + " " +  url + System.lineSeparator());
        try {
            storage.save(bookmarks.getData());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
