package commands.back;

import access.Access;
import commands.Command;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class BackModuleCommand extends Command {
    public static final String COMMAND_WORD = "backmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to module level. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        access.setModuleLevel("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
