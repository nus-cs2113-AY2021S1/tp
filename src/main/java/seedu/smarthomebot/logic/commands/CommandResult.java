package seedu.smarthomebot.logic.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        assert feedbackToUser.isEmpty() != true : "CommandResult must not accept empty feedbackToUser";
        this.feedbackToUser = feedbackToUser;
    }

}
