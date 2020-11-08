package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.list.TopicList;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.exception.topicexception.RepeatedTopicException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class AddTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(AddSubjectCommand.class.getName());
    private final String fullCommand;

    public AddTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds an instance of the <code>Topic</code> class into a <code>TopicList</code>.
     *
     * @param subject Subject that user is currently looking at
     * @throws RepeatedTopicException Thrown if there already is a topic with the same name
     * @throws InvalidTopicException Thrown if the given title is invalid
     */
    @Override
    public void execute(Subject subject) throws RepeatedTopicException, InvalidTopicException {
        logger.info("Begin checking string command to get the title of the topic to be added.");
        int startOfMessage = 4;
        int endOfMessage = fullCommand.length();
        TopicList topicList = subject.getTopics();
        if (endOfMessage <= startOfMessage) {
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }
        String title = fullCommand.substring(startOfMessage, endOfMessage).strip();
        if (title.isEmpty()) {
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }
        for (Topic topic : topicList.getList()) {
            if (topic.getTitle().equals(title)) {
                throw new RepeatedTopicException(Ui.REPEATED_TOPIC_EXCEPTION);
            }
        }
        Topic t = new Topic(title);
        topicList.add(t);
        assert t.getTitle().equals(title);
        Ui.printTopic(t, topicList);
        logger.info("Finished creating a new topic.");
        logger.fine(String.format("The topic is %s", t.getTitle()));
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
