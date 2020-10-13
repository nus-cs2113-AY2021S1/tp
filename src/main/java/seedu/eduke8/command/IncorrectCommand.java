package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

public class IncorrectCommand extends Command {
    String errorType = "";

    public IncorrectCommand(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printError(); //to implement in ui: print out messages according to string input indicating type of error
    }
}
