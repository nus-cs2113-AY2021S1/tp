package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.FailedParseException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

<<<<<<< HEAD
    public void execute(SubjectList subjectList, Storage storage) throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
=======
    /**
     * throws a FailedParseException indicating that the program does not understand user input
     *
     * @param subjectList
     * @return null
     */
    public Subject execute(SubjectList subjectList) throws FailedParseException {
        throw new FailedParseException(Ui.printFailedParseError());
>>>>>>> 87e23aa8c49cacabb05128cf6e06e6fb356d3223
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
