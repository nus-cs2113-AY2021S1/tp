package seedu.smarthomebot.logic.commands;


//@@author Ang_Cheng_Jun

/**
 * Represent the command to print error notifications to user.
 */
public class InvalidCommand extends Command {

    public final String feedbackToUser;

    /**
<<<<<<< HEAD
     * Constructor for ListCommand.
     *
     * @param feedbackToUser Message to user.
=======
     * Constructor for OffCommand.
     *
     * @param feedbackToUser Error message to be printed to user.
>>>>>>> af784b62dfa16f824e205a29149d36b2410c3b7b
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
