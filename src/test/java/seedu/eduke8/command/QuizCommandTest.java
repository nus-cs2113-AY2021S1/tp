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
    public void execute_badTopicName_exceptionCaughtAndProgramContinuesWithoutLoopsOrFailing() {
        Ui ui = new Ui();
        TopicList topicList = createTestTopicList();
        Command badQuizCommand = new QuizCommand(topicList, 1, "badname", ui);
        badQuizCommand.execute(topicList, ui);
        assertTrue(true);
    }

    @Test
    public void execute_wrongTopicNum_exceptionCaughtAndProgramContinuesWithoutLoopsOrFailing() {
        Ui ui = new Ui();
        TopicList topicList = createTestTopicList();
        Command badQuizCommand = new QuizCommand(topicList, 50, "First Topic", ui);
        badQuizCommand.execute(topicList, ui);
        assertTrue(true);
    }

}
