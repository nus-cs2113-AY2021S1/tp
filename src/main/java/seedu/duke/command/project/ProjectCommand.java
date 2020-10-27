package seedu.duke.command.project;

import seedu.duke.command.Command;

import java.util.Hashtable;

public abstract class ProjectCommand extends Command {


    /**
     * Creates a new Sprint command with arguments.
     */
    public ProjectCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();

}
