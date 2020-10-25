package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class ListSubjectCommand extends SubjectCommand {

    public void execute(SubjectList subjectList, Storage storage) {
        Ui.printSubjectList(subjectList.getList());
    }

    public boolean isExit() {
        return false;
    }
}
