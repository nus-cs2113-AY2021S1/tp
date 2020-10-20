package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.ui.Ui;

public class QuizTopicCommand extends TopicCommand {
    private String fullcommand;

    public QuizTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Topic execute(Subject subject) throws NoTopicException, InvalidTopicException {
        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidTopicException(Ui.printInvalidTopicError());
        }
        Topic quizTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                quizTopic = topic;
            }
        }
        if (quizTopic == null) {
            throw new NoTopicException(Ui.printNoTopicError());
        }
        return quizTopic;

    }

    public boolean isExit() {
        return false;
    }
}
