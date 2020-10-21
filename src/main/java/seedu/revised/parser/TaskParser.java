package seedu.revised.parser;

import seedu.revised.command.task.AddDeadlineCommand;
import seedu.revised.command.task.AddEventCommand;
import seedu.revised.command.task.AddTodoCommand;
import seedu.revised.command.task.DeleteTaskCommand;
import seedu.revised.command.task.DoneTaskCommand;
import seedu.revised.command.task.ExitTaskCommand;
import seedu.revised.command.task.FindTaskCommand;
import seedu.revised.command.task.ListTaskCommand;
import seedu.revised.command.task.SorryTaskCommand;
import seedu.revised.command.task.TaskCommand;

public class TaskParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static TaskCommand parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitTaskCommand();
        } else if (fullCommand.startsWith("done ")) {
            return new DoneTaskCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListTaskCommand();
        } else if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteTaskCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindTaskCommand(fullCommand);
        } else {
            return new SorryTaskCommand();
        }
    }
}
