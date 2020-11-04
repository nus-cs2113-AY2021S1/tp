package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.list.SubjectList;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListAllFlashcardCommand extends FlashcardCommand {
    private SubjectList subjectList;
    private static final Logger logger = Logger.getLogger(ListAllFlashcardCommand.class.getName());

    /**
     * List all subjects, topics, tasks, and flashcards.
     * @param topic topic the user is currently looking at
     */
    public void execute(Topic topic) {
        logger.info("Begin listing all while looking at topic.");
        Ui.printAll(subjectList.getList(), null, topic);
        logger.info("Finished listing all while looking at topic.");
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
