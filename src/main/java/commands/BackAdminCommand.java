package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

/**
 * Back to the Admin level.
 */
public class BackAdminCommand extends BackCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to admin level.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        access.setModuleLevel("");
    }
}
