package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.MaxNumTaskException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.EmptyDataStackException;
import seedu.exceptions.InvalidReminderException;

public class Command {
    public CommandResult execute() {
        return new CommandResult("");
    }

    public CommandResult execute(TaskMap taskMap) throws InvalidTaskNumberException {
        return new CommandResult("");
    }

    public CommandResult execute(Model model) throws InvalidPriorityException, InvalidDatetimeException,
        MaxNumTaskException, InvalidTaskNumberException, EmptyDataStackException, InvalidReminderException {
        return new CommandResult("");
    }
}
