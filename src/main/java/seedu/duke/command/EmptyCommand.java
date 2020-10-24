package seedu.duke.command;

import java.util.Hashtable;

public class EmptyCommand extends Command {
    /**
     * Creates a new abstract command.
     *
     * @param parameters
     */
    public EmptyCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
    }
}
