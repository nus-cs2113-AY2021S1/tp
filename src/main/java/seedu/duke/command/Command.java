package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public abstract class Command {
    protected boolean isExit;
    protected String command;

    /**
     * abstract class for commands.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    public abstract void execute(UserData data, Ui ui, Storage storage);

    /**
     * Signal exit program.
     *
     * @return true if user give exit seedu.duke.command
     */
    public boolean isExit() {
        return isExit;
    }
}
