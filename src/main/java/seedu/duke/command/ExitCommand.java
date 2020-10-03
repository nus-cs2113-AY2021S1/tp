package seedu.duke.command;

import seedu.duke.list.TaskList;

public class ExitCommand extends Command {

    public void execute(TaskList taskList) {
    }

    ;

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}