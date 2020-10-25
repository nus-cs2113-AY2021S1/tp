package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

public class FindSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public FindSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public void execute(SubjectList subjectList, Storage storage) {
        String[] message = this.fullcommand.split(" ");
        Ui.printFindSubject(subjectList,message[1]);
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
