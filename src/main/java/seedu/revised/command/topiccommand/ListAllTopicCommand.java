package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.command.subjectcommand.ListAllSubjectCommand;
import seedu.revised.list.SubjectList;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListAllTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(ListAllSubjectCommand.class.getName());
    private SubjectList subjectList;

    /**
     * List all subjects, topics, tasks, and flashcards.
     * @param subject subject the user is currently looking at
     */
    public void execute(Subject subject) {
        logger.info("Begin listing all at subject level.");
        Ui.printAll(subjectList.getList(), subject,null);
        logger.info("Finished listing all at subject level.");
    }

    public void setSubjectList(SubjectList subjectList) {
        this.subjectList = subjectList;
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
