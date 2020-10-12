package seedu.eduke8.quiz;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleTopicQuizTest {
    private Topic topic;

    public SingleTopicQuizTest() throws ParseException, IOException {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/example.json");
        ArrayList<Displayable> topics = topicsStorage.load();
        assert topics != null;
        topic = (Topic) topics.get(0);
        assert topic != null;
    }

    @Test
    public void startQuiz_zeroQuestions_expectEduke8Exception() {
        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, 0);

        assertThrows(Eduke8Exception.class, () -> singleTopicQuiz.startQuiz(new Ui()));
    }

    @Test
    public void startQuiz_tooManyQuestions_expectEduke8Exception() {
        int questionsInTopic = topic.getQuestionList().getCount();

        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, questionsInTopic + 1);

        assertThrows(Eduke8Exception.class, () -> singleTopicQuiz.startQuiz(new Ui()));
    }

}
