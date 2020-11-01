package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

public abstract class BackCommand extends Command {
    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to admin/module level.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage);

    @Override
    public boolean isExit() {
        return false;
    }
}
