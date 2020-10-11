package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;


public abstract class Command {
    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public abstract void execute(DisplayableList displayableList, Ui ui);

    public boolean isExit() {
        return isExit;
    }

}
