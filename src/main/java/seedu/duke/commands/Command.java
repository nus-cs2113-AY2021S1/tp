package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public abstract class Command {
    public abstract void execute(TextUi ui, ListManager listManager);

    public abstract boolean isExit();
}
