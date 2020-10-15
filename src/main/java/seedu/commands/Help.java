package seedu.commands;

import seedu.data.TaskList;

import static seedu.messages.Messages.HELP_MESSAGE;

public class Help extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(HELP_MESSAGE);
    }
}
