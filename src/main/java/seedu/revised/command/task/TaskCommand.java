package seedu.revised.command.task;

import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.task.RepeatedDateTimeException;
import seedu.revised.exception.task.TaskDeadlineException;
import seedu.revised.exception.task.TaskEventException;
import seedu.revised.exception.task.TaskTodoException;
import seedu.revised.list.TaskList;

public class TaskCommand extends Command {
    public void execute(TaskList taskList) throws TaskEventException,
            TaskTodoException, TaskDeadlineException, FailedParseException, RepeatedDateTimeException {
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
