package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

/**
 * A specific command that prints out the commands available to the user.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printHelp();
    }
}
