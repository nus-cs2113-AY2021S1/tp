package seedu.duke.logic.commands;

import seedu.duke.exceptions.CustomException;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     */
    public abstract void executeCommand() throws CustomException;

    public boolean isOngoing() {
        return true;
    }

}
