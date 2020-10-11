package seedu.duke.command.taskcommand;

import seedu.duke.command.taskcommand.TaskCommand;
import seedu.duke.task.TaskList;

public class ExitTaskCommand extends TaskCommand {

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