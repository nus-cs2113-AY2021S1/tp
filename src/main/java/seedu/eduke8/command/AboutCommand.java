package seedu.eduke8.command;

import seedu.eduke8.stats.Stats;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

/**
 * Represents the specific command that prints a message to the user with a description of what the program does.
 */
public class AboutCommand extends Command {

    public AboutCommand() {
        super();
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printAbout();
    }
}
