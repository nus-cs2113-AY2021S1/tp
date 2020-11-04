package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListAllTopicCommand extends TopicCommand {
    private SubjectList subjectList;
    private static final Logger logger = Logger.getLogger(ListAllTopicCommand.class.getName());

    /**
     * List all subjects, topics, tasks, and flashcards.
     * @param subject subject the user is currently looking at
     */
    public void execute(Subject subject) {
        logger.info("Begin listing all while looking at subject.");
        Ui.printAll(subjectList.getList(), subject,null);
        logger.info("Finished listing all while looking at subject.");
    }

    public void setSubjectList(SubjectList subjectList) {
        this.subjectList = subjectList;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
