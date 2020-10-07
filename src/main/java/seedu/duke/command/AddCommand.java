package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

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

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {

    }
}
