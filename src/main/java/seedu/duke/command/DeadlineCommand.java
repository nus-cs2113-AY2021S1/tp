package seedu.duke.command;

/**
 * Command to set deadline for personal events.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for setting deadline seedu.duke
     *
     * @param command from user input
     */
    public DeadlineCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
