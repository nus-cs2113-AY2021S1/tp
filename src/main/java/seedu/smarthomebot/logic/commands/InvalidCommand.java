package seedu.smarthomebot.logic.commands;

import static seedu.smarthomebot.commons.Messages.LINE;

//@@author Ang_Cheng_Jun
/**
 * Represent the command to print error notifications to user.
 */
public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        assert feedbackToUser.isEmpty() != true : "InvalidCommand must not accept empty feedbackToUser";
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(LINE + this.feedbackToUser);
    }

}
