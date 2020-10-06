package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

/**
 * Parent Command class.
 */
public abstract class Command {

    public static final String MESSAGE = Ui.LOGO + "\n How may i assist you?"
            .replace("\n",System.lineSeparator() + "\t");
    public static final String COMMAND_ERROR = "I'm sorry, but I don't know what that means :-(";

    boolean isExit;

    /**
     * Initializes command to execute and set exit status to false.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes command.
     *
     * @param patients   PatientList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws RexException If there is issue executing command.
     */
    public abstract void execute(PatientList patients, Ui ui, Storage storage) throws RexException;

    /**
     * Returns Rex's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }
}
