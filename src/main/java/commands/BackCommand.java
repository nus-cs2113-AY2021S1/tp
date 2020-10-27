package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import storage.Storage;
import ui.Ui;

public abstract class BackCommand extends Command {
    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to up-level directory. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException;

    @Override
    public boolean isExit() {
        return false;
    }
}
