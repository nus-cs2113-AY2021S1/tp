package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;

public class ExitSubjectCommand extends SubjectCommand {

    public void execute(SubjectList subjectList, Storage storage) {

    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
