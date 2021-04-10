package seedu.hdbuy.command;

import seedu.hdbuy.ui.TextUi;

public class HelpCommand extends Command {
    @Override public void execute() {
        TextUi.showHelp();
    }
}
