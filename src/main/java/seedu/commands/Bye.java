package seedu.commands;

import seedu.data.TimerCanceler;

import static seedu.messages.Messages.BYE_MESSAGE;

public class Bye extends GeneralCommand {
    public static final String COMMAND_WORD = "bye";

    public CommandResult execute() {
        CommandResult result = new CommandResult(BYE_MESSAGE);
        TimerCanceler.cancel();
        result.setExit(true);
        return result;
    }
}
