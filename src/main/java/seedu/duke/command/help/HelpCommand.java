package seedu.duke.command.help;

import seedu.duke.command.Command;

import java.util.Hashtable;

public abstract class HelpCommand extends Command {

    public HelpCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();

    public abstract void logExecution();
}
