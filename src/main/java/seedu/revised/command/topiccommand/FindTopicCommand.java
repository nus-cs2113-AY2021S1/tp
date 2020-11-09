package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.command.subjectcommand.FindSubjectCommand;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.list.TopicList;
import seedu.revised.command.taskcommand.FindTaskCommand;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class FindTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(FindSubjectCommand.class.getName());
    private final String fullCommand;

    public FindTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Searches a <code>Subject</code> for topics and tasks that contain a search query.
     *
     * @param subject Subject to search for topics and tasks in
     * @throws InvalidTopicException Thrown if the search term is invalid
     */
    public void execute(Subject subject) throws InvalidTopicException {
        logger.info("Begin checking string command to get the search query.");
        TopicList topicList = subject.getTopics();
        String[] message = this.fullCommand.split("\\s+", 2);
        if (message.length <= 1 || message[1].isEmpty()) {  // empty argument
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }

        Ui.printFindTopic(topicList, message[1].strip());
        FindTaskCommand findTaskCommand = new FindTaskCommand(this.fullCommand);
        findTaskCommand.execute(subject.getTasks());
        logger.info("Finished searching for subjects matching the query.");
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
