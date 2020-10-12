package commands;

import access.Access;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public class BackChapterCommand extends Command {
    public static final String COMMAND_WORD = "backchapter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to chapter level. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        access.setChapterLevel("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
