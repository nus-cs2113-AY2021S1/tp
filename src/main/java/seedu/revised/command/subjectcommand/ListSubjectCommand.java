package seedu.revised.command.subjectcommand;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ListSubjectCommand.class.getName());

    /**
     * List subjects in a <code>SubjectList</code>.
     *
     * @param subjectList the <code>SubjectList</code> instance of the <code>SubjectList</code>
     *                    class for the user to list subjects from
     * @param storage     Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) {
        logger.info("Begin listing subjects in the SubjectList.");
        Ui.printSubjectList(subjectList.getList());
        logger.info("Finished listing the subjects in the SubjectList.");
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
