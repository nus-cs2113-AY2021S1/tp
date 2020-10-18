package seedu.commands;

import seedu.data.TaskMap;

import static seedu.messages.Messages.HELP_MESSAGE;

public class Help extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(TaskMap tasks) {
        return new CommandResult(HELP_MESSAGE);
    }
}
