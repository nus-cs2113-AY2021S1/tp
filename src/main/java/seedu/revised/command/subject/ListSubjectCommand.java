package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ListSubjectCommand.class.getName());

    /**
     * List subjects in a <code>SubjectList</code>.
     *
     * @param subjectList the <code>SubjectList</code> instance of the <code>SubjectList</code> class for the user to list subjects from.
     * @return null
     */
    public Subject execute(SubjectList subjectList) {
        logger.info("Begin listing subjects in the SubjectList.");

        Ui.printSubjectList(subjectList.getList());
        logger.info("Finish listing the subjects in the SubjectList.");
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
