package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.FailedParseException;
import seedu.revised.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

    /**
     * throws a FailedParseException indicating that the program does not understand user input
     *
     * @param subjectList
     * @return null
     */
    public Subject execute(SubjectList subjectList) throws FailedParseException {
        throw new FailedParseException(Ui.printFailedParseError());
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
