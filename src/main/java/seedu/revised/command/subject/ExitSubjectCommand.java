package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;

public class ExitSubjectCommand extends SubjectCommand {

    /**
     * Does nothing in this case but needed since this method was implemented
     * from an abstract class.
     *
     * @param subjectList Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     * @param storage     Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) {
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
