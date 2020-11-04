package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.*;

public class Command {
    public CommandResult execute() {
        return new CommandResult("");
    }

    public CommandResult execute(TaskMap taskMap) throws InvalidTaskNumberException {
        return new CommandResult("");
    }

    public CommandResult execute(Model model) throws InvalidPriorityException, InvalidDatetimeException,
        MaxNumTaskException, InvalidTaskNumberException, EmptyDataStackException, InvalidCommandException {
        return new CommandResult("");
    }
}
