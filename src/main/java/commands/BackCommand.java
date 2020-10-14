package commands;

import access.Access;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class BackCommand extends Command {
    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to up-level directory. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.isChapterLevel()) {
            access.setChapterLevel("");
        } else if (access.isModuleLevel()) {
            access.setModuleLevel("");
        } else {
            System.out.println("Back command can only be called at module and chapter level.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
