//@@author fchensan

package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditBookmarkCommand extends Command {
    public static final String EDIT_KW = "edit";

    public static final String FIELD_KW_DESCRIPTION = "desc";
    public static final String FIELD_KW_URL = "url";

    private int bookmarkIndex;
    private String fieldToEdit;
    private String newValue;

    public EditBookmarkCommand(String command) throws ZoomasterException {
        assert command.startsWith(EDIT_KW);

        Pattern parser = Pattern.compile(
                EDIT_KW + " (?<fieldToEdit>[a-zA-Z]+)\\s+(?<bookmarkIndex>\\d+)\\s+(?<newValue>[\\w/.:]+)"
        );
        Matcher matcher = parser.matcher(command);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        bookmarkIndex = Integer.parseInt(matcher.group("bookmarkIndex")) - 1;
        fieldToEdit = matcher.group("fieldToEdit");
        newValue = matcher.group("newValue");
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        Bookmark bookmark = null;
        try {
            bookmark = bookmarks.getBookmark(bookmarkIndex);
        } catch (ZoomasterException e) {
            throw new ZoomasterException(ZoomasterExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS);
        }
        String message;

        switch (fieldToEdit) {
        case FIELD_KW_DESCRIPTION:
            bookmark.setDescription(newValue);
            message = "Bookmark description updated!";
            break;
        case FIELD_KW_URL:
            bookmark.setUrl(newValue);
            message = "Bookmark url updated!";
            break;
        default:
            message = "Unrecognized field!";
            break;
        }

        ui.print(message + "\n");
    }
}
