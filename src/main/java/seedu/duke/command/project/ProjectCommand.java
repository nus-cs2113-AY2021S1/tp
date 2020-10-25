package seedu.duke.command.project;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;

import java.util.Hashtable;

public abstract class ProjectCommand extends Command {


    /**
     * Creates a new Sprint command with arguments.
     */
    public ProjectCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public abstract void execute();

}
