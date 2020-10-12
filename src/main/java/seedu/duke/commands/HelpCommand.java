package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class HelpCommand extends Command {

    public void execute(TextUi ui) {
        ui.printHelpPage();
    }

    public boolean isExit() {
        return false;
    }
}
