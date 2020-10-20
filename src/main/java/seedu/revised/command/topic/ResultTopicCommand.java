package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.ui.Ui;

public class ResultTopicCommand extends TopicCommand {
    private String fullcommand;

    public ResultTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Topic execute(Subject subject) throws NoTopicException, InvalidTopicException {


        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidTopicException(Ui.printEnterTopicError());
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
        Ui.printTopicResults(quizTopic);
        return null;
    }
}
