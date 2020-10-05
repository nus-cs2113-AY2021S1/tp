package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;

public abstract class Command {

    protected boolean isExitCommand = false;

    /**
     * Execution of command depends on which command subclass the command belongs to.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an ExitCommand.
     */
    public boolean isExit() {
        return isExitCommand;
    }
}
