package seedu.duke.Command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command and throws a Duke exception when the command is invalid.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Returns true if the command is exit.
     */
    public boolean isExit() {
        return false;
    }

}
