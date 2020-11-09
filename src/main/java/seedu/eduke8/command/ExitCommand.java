package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

/**
 * A specific command that allows user to terminate the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        isExit = true;
    }
}
