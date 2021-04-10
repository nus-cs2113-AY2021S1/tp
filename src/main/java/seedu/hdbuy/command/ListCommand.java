package seedu.hdbuy.command;

import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.ui.TextUi;

public class ListCommand extends Command {

    @Override public void execute() {
        TextUi.showParameters(UserInput.getInputs());
    }
}
