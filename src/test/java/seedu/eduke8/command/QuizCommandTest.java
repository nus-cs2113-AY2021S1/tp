package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.topic.TopicList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizCommandTest extends Eduke8Test {
    private BookmarkList bookmarks;
    private TopicList topicList;

    QuizCommandTest() {
        super();
        bookmarks = createTestBookmarkList();
        topicList = createTestTopicList();
    }

    @Test
    void execute_badTopicName_exceptionCaughtAndProgramContinuesWithoutLoopsOrFailing() {
        Command badQuizCommand =
                new QuizCommand(topicList, 1, NONSENSE_DESCRIPTION, ui, bookmarks, TIMER);
        badQuizCommand.execute(topicList, ui);
        assertTrue(true);
    }

    @Test
    void execute_wrongTopicNum_exceptionCaughtAndProgramContinuesWithoutLoopsOrFailing() {
        Command badQuizCommand =
                new QuizCommand(topicList, 50, PLACEHOLDER_TOPIC_ONE_DESCRIPTION, ui, bookmarks, TIMER);
        badQuizCommand.execute(topicList, ui);
        assertTrue(true);
    }

}
