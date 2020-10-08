package commands;

import access.Access;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class BackModuleCommand extends Command {
    public static final String COMMAND_WORD = "backmodule";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        access.setModuleLevel("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
