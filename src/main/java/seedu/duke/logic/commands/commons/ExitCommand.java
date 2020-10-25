package seedu.duke.logic.commands.commons;

import seedu.duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void executeCommand() {
        Ui.printExitMessage();
    }

    @Override
    public boolean isOngoing() {
        return false;
    }
}
