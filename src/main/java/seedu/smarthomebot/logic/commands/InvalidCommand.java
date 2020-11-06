package seedu.smarthomebot.logic.commands;


//@@author Ang_Cheng_Jun
/**
 * Represent the command to print error notifications to user.
 */
public class InvalidCommand extends Command {

    public final String feedbackToUser;

    /**
     * Constructor for ListCommand.
     *
     * @param feedbackToUser Message to user.
     */
    public InvalidCommand(String feedbackToUser) {
        assert feedbackToUser.isEmpty() != true : "InvalidCommand must not accept empty feedbackToUser";
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Executing the InvalidCommand.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

}
