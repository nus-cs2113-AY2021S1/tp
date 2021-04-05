package seedu.hdbuy.command;

import seedu.hdbuy.ui.TextUi;

public class CloseCommand extends Command {

    @Override public void execute() {
        TextUi.showExit();
    }
}
