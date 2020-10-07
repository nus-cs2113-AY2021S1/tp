package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

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

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {

    }
}
