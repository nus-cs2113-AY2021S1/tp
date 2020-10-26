package seedu.duke.logic.commands.commons;

import seedu.duke.exceptions.CustomException;
import seedu.duke.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void executeCommand() throws CustomException {
        Ui.printHelp();
    }
}
