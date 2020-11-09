package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public abstract class GoCommand extends Command {
    public static final String COMMAND_WORD = "go";

    public static final String MODULE_PARAMETERS = "MODULE_INDEX";
    public static final String CHAPTER_PARAMETERS = "CHAPTER_INDEX";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter / module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "         " + COMMAND_WORD + " 2\n";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage) throws IOException;

    @Override
    public boolean isExit() {
        return false;
    }
}

