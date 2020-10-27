package seedu.duke.command.task;

import seedu.duke.command.Command;

import java.util.Hashtable;

public abstract class TaskCommand extends Command {
    public TaskCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();
}