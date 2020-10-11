package seedu.duke.command.taskcommand;

import seedu.duke.command.subjectcommand.SubjectCommand;
import seedu.duke.ui.Ui;

public class SorryTaskCommand extends TaskCommand {

    public void execute() {
        Ui.printError();
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
