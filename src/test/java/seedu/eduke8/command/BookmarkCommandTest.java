package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

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
}
