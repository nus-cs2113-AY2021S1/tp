package seedu.eduke8.quiz;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.Topic;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleTopicQuizTest extends Eduke8Test {
    public static final int TIMEOUT = 1;
    private BookmarkList bookmarks;
    private Topic topic;

    SingleTopicQuizTest() {
        bookmarks = createTestBookmarkList();
        topic = createTestTopic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);
    }

    @Test
    void startQuiz_zeroQuestions_expectEduke8Exception() {
        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, 0, bookmarks, TIMEOUT);

        assertThrows(Eduke8Exception.class, () -> singleTopicQuiz.startQuiz(ui));
    }

    @Test
    void startQuiz_tooManyQuestions_expectEduke8Exception() {
        int questionsInTopic = topic.getQuestionList().getCount();

        SingleTopicQuiz singleTopicQuiz =
                new SingleTopicQuiz(topic, questionsInTopic + 1, bookmarks, TIMEOUT);

        assertThrows(Eduke8Exception.class, () -> singleTopicQuiz.startQuiz(ui));
    }

}
