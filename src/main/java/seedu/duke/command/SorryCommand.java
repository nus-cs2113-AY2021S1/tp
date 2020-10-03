package seedu.duke.command;

import seedu.duke.list.TaskList;
import seedu.duke.ui.Ui;

public class SorryCommand extends Command {

    /**
     * Prints an error if the user fails to input correctly.
     *
     * @param taskList nil
     */
    @Override
    public void execute(TaskList taskList) {
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
