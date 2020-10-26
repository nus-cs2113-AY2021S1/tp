package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.FailedParseException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

    public void execute(SubjectList subjectList, Storage storage) throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
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
