package seedu.quotesify.commands;

import seedu.quotesify.ui.TextUi;

public class HelpCommand extends Command {

    public void execute(TextUi ui) {
        ui.printHelpPage();
    }

    public boolean isExit() {
        return false;
    }
}
