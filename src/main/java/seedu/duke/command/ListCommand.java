package seedu.duke.command;

/**
 * Command to list events.
 */
public class ListCommand extends Command {
    /**
     * Constructor for listing events seedu.duke
     *
     * @param command from user input
     */
    public ListCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
