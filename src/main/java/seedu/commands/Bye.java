package seedu.commands;

import seedu.data.TaskList;

import static seedu.messages.Messages.BYE_MESSAGE;

public class Bye extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(BYE_MESSAGE);
    }
}
