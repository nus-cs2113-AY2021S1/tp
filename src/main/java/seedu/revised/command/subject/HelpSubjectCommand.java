package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.ui.Ui;

public class HelpSubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        Ui.printSubjectHelp();
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
