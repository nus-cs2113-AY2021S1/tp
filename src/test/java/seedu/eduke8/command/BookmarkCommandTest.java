package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookmarkCommandTest extends Eduke8Test {

    @Test
    void execute_normal_questionHasBeenBookmarked() {
        BookmarkList bookmarkList = createTestBookmarkList();
        Question question = createTestQuestion("description");
        assertFalse(question.isBookmarked());
        Command command = new BookmarkCommand(question, "storing", bookmarkList);
        command.execute(createTestTopicList(), new Ui());
        assertTrue(question.isBookmarked());
    }

    @Test
    void constructor_bookmarksNull_assertionErrorThrown() {
        BookmarkList bookmarkList = null;
        Question question = createTestQuestion("description");
        assertThrows(AssertionError.class, () -> {
            Command command = new BookmarkCommand(question, "storing", bookmarkList);
        });
    }

    @Test
    void constructor_correctInput_alreadyBookmarked() {
        BookmarkList bookmarkList = createTestBookmarkList();
        Displayable question1 = bookmarkList.find("First question description.");
        Question question2 = (Question) question1;
        question2.markAsBookmarked();
        BookmarkCommand command = new BookmarkCommand(question2, "storing", bookmarkList);
        command.execute(createTestTopicList(), new Ui());
        assertTrue(question2.isBookmarked());
    }

    @Test
    void execute_normal_listBookmarks() {
        BookmarkList bookmarkList = createTestBookmarkList();
        Command command = new BookmarkCommand("listing", bookmarkList);
        command.execute(createTestTopicList(), new Ui());
        assertTrue(true);
    }

    @Test
    void execute_badCommand_nothingHappens() {
        BookmarkList bookmarkList = createTestBookmarkList();
        Command command = new BookmarkCommand("badcommand", bookmarkList);
        command.execute(createTestTopicList(), new Ui());
        assertTrue(true);
    }
}
