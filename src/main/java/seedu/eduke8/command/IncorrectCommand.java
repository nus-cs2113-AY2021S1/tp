package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

/**
 * A specific command that indicates by a printed message that an error was encountered while parsing the user's input.
 */
public class IncorrectCommand extends Command {
    String errorMessage;

    public IncorrectCommand(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printError(errorMessage);
    }
}
