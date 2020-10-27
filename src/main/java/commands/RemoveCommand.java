package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes %1$s based on the index in the list. \n"
            + "Parameters: %2$s_INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
