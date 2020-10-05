package seedu.duke.command;

/**
 * Command to check availability.
 */
public class CheckCommand extends Command {
    /**
     * Constructor for checking availability seedu.duke
     *
     * @param command from user input
     */
    public CheckCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
