package seedu.duke.command;

/**
 * Command to set goals.
 */
public class GoalCommand extends Command {
    /**
     * Constructor for setting goals seedu.duke
     *
     * @param command from user input
     */
    public GoalCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

//    @Override
//    public void execute(TaskList tasks, Ui ui, Storage storage) {
//
//    }
}
