package seedu.commands;

import seedu.data.TaskMap;

import static seedu.messages.Messages.CLEAR_MESSAGE;

public class Clear extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public CommandResult execute(TaskMap tasks) {
        tasks.clear();
        return new CommandResult(CLEAR_MESSAGE);
    }
}
