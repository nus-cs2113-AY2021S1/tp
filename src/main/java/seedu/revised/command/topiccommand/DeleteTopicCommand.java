package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.list.TopicList;
import seedu.revised.ui.Ui;

import java.util.List;
import java.util.logging.Logger;

public class DeleteTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(AddSubjectCommand.class.getName());
    private final String fullCommand;

    public DeleteTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a <code>Topic</code> in a <code>Subject</code>.
     *
     * @param subject the <code>Subject</code> instance of the Subject class for the user to delete from
     */
    public void execute(Subject subject) throws NumberFormatException {
        logger.info("Begin checking string command to get the topic to be deleted.");
        String[] message = this.fullCommand.split(" ");
        TopicList topicList = subject.getTopics();
        int number = Integer.parseInt(message[1]);
        List<Topic> topics = topicList.getList();
        int initialSize = topics.size();
        Topic topic = topics.get(number - 1);

        topics.remove(number - 1);
        Ui.printTopicDelete(topic, topics.size());
        assert initialSize - 1 == topics.size();
        logger.info("Finished deleting the topic.");
        logger.fine(String.format("The subject is %s", topic.getTitle()));
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
