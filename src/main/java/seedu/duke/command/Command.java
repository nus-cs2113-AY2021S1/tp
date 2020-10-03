package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

/**
 * Various commands to use depending on the various inputs given by the user
 */
public abstract class Command {
    public abstract void execute(TaskList taskList) throws DukeException;
    public abstract boolean isExit();
}