package seedu.duke.logic.commands.commons;

import seedu.duke.exceptions.CustomException;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    protected boolean isValid;

    /**
     * Executes the command and returns the result message.
     */
    public abstract void executeCommand() throws CustomException;

    public boolean isOngoing() {
        return true;
    }

    public boolean isValid() {
        return isValid;
    }
}
