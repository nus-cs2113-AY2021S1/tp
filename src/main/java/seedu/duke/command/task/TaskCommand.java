package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class TaskCommand extends Command {

    /**
     * Creates a new Sprint command with arguments.
     */
    public TaskCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public abstract void execute() throws DukeException;

}