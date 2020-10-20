package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Timetable;

import java.util.List;

/**
 * Represents the user command exit the Duke program.
 */
public class AddBookmarkCommand extends Command {
    public static final String ADD_KW = "add";
    public String description;
    public String url;

    /**
     * Constructs a new AddBookmarkCommand instance and stores the information of the bookmark given by the input.
     *
     * @throws DukeException if input command is invalid, if the description is empty or if the url is invalid.
     */
    public AddBookmarkCommand(String input) throws DukeException {
        assert input.startsWith(AddBookmarkCommand.ADD_KW) : "input should always start with \"add\"";
        String command = input.substring(AddBookmarkCommand.ADD_KW.length());
        if (command.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_COMMAND, ADD_KW);
        }
        if (!command.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        List<String> descAndUrl = Bookmark.extractDescriptionAndUrl(command.trim());
        description = descAndUrl.get(0);
        url = descAndUrl.get(1);
    }

    /**
     * Adds the bookmark to the bookmark list and saves the bookmarks list in the text file.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @param bookmarkStorage The storage for saving and loading bookmarks.
     * @param slotStorage The storage for saving and loading slots.
     * @throws DukeException if there is an error when saving the bookmark.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        Bookmark bookmark = new Bookmark(description, url);
        String message = bookmarks.addBookmark(bookmark);
        ui.print(message);
        bookmarkStorage.save(bookmarks.getData());
    }
}
