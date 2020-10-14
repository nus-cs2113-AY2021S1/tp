package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

public class IncorrectCommand extends Command {
    String errorMessage = "";

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printError(errorMessage); // print out messages according to string input indicating type of error
    }
}
