package seedu.duke.command;

import seedu.duke.tasks.TaskList;

/**
 * Various commands to use depending on the various inputs given by the user
 */
public abstract class Command {
    public abstract void execute(TaskList taskList);
    public abstract boolean isExit();
}