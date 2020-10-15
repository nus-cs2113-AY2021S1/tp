package seedu.commands;

import seedu.data.TaskMap;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidTaskNumberException;

public abstract class Command {

    public abstract CommandResult execute(TaskMap tasks)
            throws InvalidPriorityException, InvalidTaskNumberException, InvalidDatetimeException;

}
