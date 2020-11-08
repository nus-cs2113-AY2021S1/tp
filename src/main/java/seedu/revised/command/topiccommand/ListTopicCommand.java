package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.command.subjectcommand.ListSubjectCommand;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(ListSubjectCommand.class.getName());

    /**
     * Lists all topics and tasks in a subject.
     *
     * @param subject The subject that the user is currently looking at
     */
    public void execute(Subject subject) {
        logger.info("Begin listing topics in Subject.");
        Ui.printTopicList(subject);
        logger.info("Finished listing topics in Subject.");
        logger.info("Begin listing tasks in the Subject.");
        Ui.printTaskList(subject);
        logger.info("Begin listing tasks in Subject.");
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
