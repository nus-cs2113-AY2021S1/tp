package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class HelpSubjectCommand extends SubjectCommand {

    /**
     * Prints help function.
     *
     * @param subjectList Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     * @param storage     Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) {
        Ui.printSubjectHelp();
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
