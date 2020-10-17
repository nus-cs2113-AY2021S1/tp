package seedu.smarthomebot.commands;

import static seedu.smarthomebot.common.Messages.LINE;

public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(LINE + this.feedbackToUser);
    }

}
