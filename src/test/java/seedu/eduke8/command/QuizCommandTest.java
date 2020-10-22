package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.QuizQuestionsManager;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizCommandTest extends Eduke8Test {

    @Test
    public void execute_badTopicName_exceptionThrown(){
        Ui ui = new Ui();
        TopicList topicList = createTestTopicList();
        Command badQuizCommand = new QuizCommand(topicList,1, "badname", ui);
        Eduke8Exception exception = assertThrows(Eduke8Exception.class, () -> {
            topicList.find("badname");
        });
    }

    @Test
    public void execute_wrongTopicNum_exceptionThrown(){
        Ui ui = new Ui();
        TopicList topicList = createTestTopicList();
        Eduke8Exception ee = assertThrows(Eduke8Exception.class, () -> {
            new QuizQuestionsManager(50, createTestQuestionList().getInnerList());
        });
    }

}
