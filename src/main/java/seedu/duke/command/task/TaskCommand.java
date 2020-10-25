package seedu.duke.command.task;

import seedu.duke.command.Command;

import java.util.Hashtable;

public abstract class TaskCommand extends Command {
    public TaskCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public abstract void execute();
}