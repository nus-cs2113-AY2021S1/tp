package seedu.duke.command.sprint;

import seedu.duke.command.Command;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class SprintCommand extends Command {

    /**
     * Creates a new Sprint command with arguments.
     */
    public SprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public abstract void execute();

}
