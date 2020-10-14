package seedu.quotesify.commands;

import seedu.quotesify.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TextUi ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
