package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

public class HelpSubjectCommand extends SubjectCommand {

    /**
     * Prints help function.
     *
     * @param subjectList
     * @return null
     */
    public Subject execute(SubjectList subjectList) {
        Ui.printSubjectHelp();
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
