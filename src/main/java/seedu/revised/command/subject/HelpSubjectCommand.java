package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class HelpSubjectCommand extends SubjectCommand {

    /**
     * Prints help function.
     *
     * @param subjectList
     * @param storage
     */
    public void execute(SubjectList subjectList, Storage storage) {
        Ui.printSubjectHelp();
    }

    public boolean isExit() {
        return false;
    }
}
