package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

/**
 * Lists all the command available.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of commands available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        ui.showHelpList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
