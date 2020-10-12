package seedu.duke.command.taskcommand;

import seedu.duke.card.Subject;
import seedu.duke.command.Command;
import seedu.duke.exception.TaskException;
import seedu.duke.task.TaskList;

public class TaskCommand extends Command {
    public void execute(TaskList taskList) throws TaskException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
