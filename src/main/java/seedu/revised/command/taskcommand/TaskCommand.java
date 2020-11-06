package seedu.revised.command.taskcommand;

import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.taskexception.RepeatedDateTimeException;
import seedu.revised.exception.taskexception.TaskDeadlineException;
import seedu.revised.exception.taskexception.TaskEventException;
import seedu.revised.exception.taskexception.TaskTodoException;
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
