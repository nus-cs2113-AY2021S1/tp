package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.ui.Ui;

public class ListSubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        Ui.printSubjectList(subjectList.getList());
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
