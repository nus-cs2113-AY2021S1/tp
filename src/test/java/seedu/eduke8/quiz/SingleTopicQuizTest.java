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
    @Test
    public void startQuiz_zeroQuestions_expectEduke8Exception() throws ParseException, IOException {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/example.json");

        ArrayList<Displayable> topics = topicsStorage.load();

        assert topics != null;

        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz((Topic) topics.get(0), 0);

        assertThrows(Eduke8Exception.class, () -> singleTopicQuiz.startQuiz(new Ui()));
    }

}
