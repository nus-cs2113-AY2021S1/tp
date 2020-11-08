package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

public class BackModuleCommand extends BackCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to module level.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        access.setChapterLevel("");
    }
}
