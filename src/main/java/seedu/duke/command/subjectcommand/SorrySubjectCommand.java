package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        Ui.printError();
        return null;
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
