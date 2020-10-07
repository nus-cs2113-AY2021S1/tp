package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.UserData;

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
        ui.printByeMessage();
    }

}
