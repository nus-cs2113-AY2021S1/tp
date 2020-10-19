package seedu.quotesify.commands;

import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TextUi ui) {
        Storage.save();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
