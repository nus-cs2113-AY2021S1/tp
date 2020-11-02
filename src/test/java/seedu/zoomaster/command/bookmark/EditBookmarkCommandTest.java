package seedu.zoomaster.command.bookmark;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EditBookmarkCommandTest {
    Timetable timetable = new Timetable();
    BookmarkList bookmarks;
    Ui ui = new Ui();

    private void setupBookmarks() {
        bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("luminus", "www.nus.edu.sg/luminus"));
        bookmarks.addBookmark(new Bookmark("google", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("youtube", "www.youtube.com"));
    }

    @Test
    void constructor_invalidInput_throwsZoomasterException() {
        setupBookmarks();
        String input = EditBookmarkCommand.EDIT_KW + " desc";

        try {
            EditBookmarkCommand command = new EditBookmarkCommand(input);
            command.execute(bookmarks, timetable, ui);
            fail();
        } catch (ZoomasterException e) {
            assertEquals(e.getError(), ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }
    }

    @Test
    void execute_editDesc_changesSuccessfully() {
        setupBookmarks();

        String newDescription = "homepage";
        String input = EditBookmarkCommand.EDIT_KW + " desc 2 " + newDescription;

        try {
            EditBookmarkCommand command = new EditBookmarkCommand(input);
            command.execute(bookmarks, timetable, ui);
            assertEquals(bookmarks.getBookmark(1).getDescription(), newDescription);
        } catch (ZoomasterException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void execute_editUrl_changesSuccessfully() {
        setupBookmarks();

        String newUrl = "www.yahoo.com";
        String input = EditBookmarkCommand.EDIT_KW + " url 2 " + newUrl;

        try {
            EditBookmarkCommand command = new EditBookmarkCommand(input);
            command.execute(bookmarks, timetable, ui);
            assertEquals(bookmarks.getBookmark(1).getUrl(), newUrl);
        } catch (ZoomasterException e) {
            fail(e.getMessage());
        }
    }
}
