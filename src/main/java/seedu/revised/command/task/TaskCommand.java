package seedu.revised.command.task;

import seedu.revised.command.Command;
import seedu.revised.exception.task.TaskException;
import seedu.revised.task.TaskList;

public class TaskCommand extends Command {
    public void execute(TaskList taskList) throws TaskException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
