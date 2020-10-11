package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class QuizCommand extends Command {
    private TopicList topicList;
    private int numOfQuestions;
    private String topicName;
    private Ui ui;

    public QuizCommand(TopicList topicList, int numOfQuestions, String topicName, Ui ui) {
        this.topicList = topicList;
        this.numOfQuestions = numOfQuestions;
        this.topicName = topicName;
        this.ui = ui;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        Topic topic = (Topic) topicList.find(topicName);
        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, numOfQuestions);
        singleTopicQuiz.startQuiz(ui);
    }
}
