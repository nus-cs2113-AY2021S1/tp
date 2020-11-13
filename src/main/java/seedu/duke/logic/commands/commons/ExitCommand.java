package seedu.duke.logic.commands.commons;

import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isValid = true;
    }

    @Override
    public void executeCommand() {
        Ui.printExitMessage();
        isValid = true;
    }

    @Override
    public boolean isOngoing() {
        return false;
    }
}
