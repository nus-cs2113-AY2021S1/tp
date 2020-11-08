package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ResultTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(QuizTopicCommand.class.getName());
    private final String fullCommand;

    public ResultTopicCommand(String fullcommand) {
        this.fullCommand = fullcommand;
    }

    public void execute(Subject subject) throws NoTopicException, InvalidTopicException {
        logger.info("Begin finding the topic for which the results feature has to be called.");

        String[] message = this.fullCommand.split("\\s+", 2);
        if (message.length == 1 || message[1].isEmpty()) {
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }
        Topic resultTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().equals(message[1].strip())) {
                resultTopic = topic;
                break;
            }
        }
        if (resultTopic == null) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        logger.info("Finish reading the command to find the topic for the result feature.Now, the "
                + "application prints" + "the results.");
        logger.fine(String.format("The subject is %s", resultTopic.getTitle()));
        Ui.printTopicResults(resultTopic);
    }

    /**
     * Checks whether the the user exits the subject.
     *
     * @return <code>true</code> If user exits the subject
     */
    public boolean isExit() {
        return false;
    }
}
