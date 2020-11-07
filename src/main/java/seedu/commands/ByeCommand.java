package seedu.commands;

import seedu.data.Timers;

import static seedu.messages.Messages.BYE_MESSAGE;

public class ByeCommand extends GeneralCommand {
    public static final String COMMAND_WORD = "bye";

    public CommandResult execute() {
        CommandResult result = new CommandResult(BYE_MESSAGE);
        Timers.cancel();
        result.setExit(true);
        return result;
    }
}
