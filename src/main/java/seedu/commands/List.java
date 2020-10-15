package seedu.commands;

import seedu.data.TaskMap;

import static seedu.messages.Messages.LIST_MESSAGE;

public class List extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(TaskMap tasks) {
        return new CommandResult(LIST_MESSAGE, tasks);
    }
}
