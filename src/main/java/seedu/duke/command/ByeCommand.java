package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

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

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {

    }
}
