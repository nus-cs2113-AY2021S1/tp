package seedu.duke.command;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    /**
     * Constructor for repeating events seedu.duke
     *
     * @param command from user input
     */
    public RepeatCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
