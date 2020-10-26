package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.exception.FailedParseException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

    /**
     * Throws an exception to show invalid user input.
     *
     * @param subjectList           Does nothing in this case but needed since this method was implemented
     *                              from an abstract class
     * @param storage               Does nothing in this case but needed since this method was implemented
     *                              from an abstract class
     * @throws FailedParseException If user input is invalid
     */
    public void execute(SubjectList subjectList, Storage storage) throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
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
