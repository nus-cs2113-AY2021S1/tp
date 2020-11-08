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

    /**
     * Do nothing, exits the program.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
    }

}
