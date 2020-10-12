package seedu.commands;

import seedu.data.TaskList;
import seedu.exceptions.InvalidPriorityException;

public abstract class Command {

    public abstract CommandResult execute(TaskList tasks) throws InvalidPriorityException;

}
