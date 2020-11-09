package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    /**
     * Carries out the command and returns the outcome of the execution.
     * @param displayableList an object from a class that extends the DisplayableList interface
     * @param ui              a Ui object to manage user interactions
     */
    public abstract void execute(DisplayableList displayableList, Ui ui);

    /**
     * Represents the exit status of the program.
     * @return isExit the boolean controlling if the program exits or not
     */
    public boolean isExit() {
        return isExit;
    }

}
