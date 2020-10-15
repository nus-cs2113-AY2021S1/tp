package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private TopicList topicList;
    private int numOfQuestions;
    private String topicName;
    private Ui ui;

    public QuizCommand(TopicList topicList, int numOfQuestions, String topicName, Ui ui) {
        assert topicList != null;
        assert numOfQuestions != 0;

        this.topicList = topicList;
        this.numOfQuestions = numOfQuestions;
        this.topicName = topicName;
        this.ui = ui;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        try {
            Topic topic = (Topic) topicList.find(topicName);
            SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, numOfQuestions);
            singleTopicQuiz.startQuiz(ui);
        } catch (Eduke8Exception e) {
            LOGGER.log(Level.WARNING, "QuizCommand Execution: An error took place.");
            ui.printError(e.getMessage());
        }
    }
}
