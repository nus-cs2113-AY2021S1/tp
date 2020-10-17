package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.card.InvalidTopicCommand;
import seedu.revised.exception.card.NoTopicException;

public class QuizTopicCommand extends TopicCommand {
    private String fullcommand;

    public QuizTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Topic execute(Subject subject) throws NoTopicException, InvalidTopicCommand {
        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidTopicCommand();
        }
        Topic quizTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                quizTopic = topic;
            }
        }
        if (quizTopic == null) {
            throw new NoTopicException();
        }
        return quizTopic;

    }

    public boolean isExit() {
        return false;
    }
}
