package seedu.duke.command;

/**
 * Command to end program seedu.duke.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for exiting the program.
     */
    public ByeCommand() {
        this.isExit = true;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
