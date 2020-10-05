package seedu.duke.command;

/**
 * Command to add events.
 */
public class AddCommand extends Command {
    /**
     * Constructor for adding events seedu.duke
     *
     * @param command from user input
     */
    public AddCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
