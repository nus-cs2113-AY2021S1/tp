package seedu.quotesify.commands;

import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class HelpCommand extends Command {

    @Override
    public void execute(TextUi ui, Storage storage) {
        ui.printHelpPage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
