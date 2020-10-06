package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TextUi ui, ListManager listManager) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
