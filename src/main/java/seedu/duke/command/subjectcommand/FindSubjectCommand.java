package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.ui.Ui;

public class FindSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public FindSubjectCommand(String fullcommand){
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) {
        String[] message = this.fullcommand.split(" ");
        Ui.printFindSubject(subjectList,message[1]);
        return null;
    }

    /**
     * Checks whether the the user exits the program
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit(){
        return false;
    }
}
