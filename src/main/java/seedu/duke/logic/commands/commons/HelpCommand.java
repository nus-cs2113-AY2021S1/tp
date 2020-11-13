package seedu.duke.logic.commands.commons;

import seedu.duke.exceptions.CustomException;
import seedu.duke.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super.isValid = true;
    }

    @Override
    public void executeCommand() throws CustomException {
        Ui.printHelp();
    }
}
