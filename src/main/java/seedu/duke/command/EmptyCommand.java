package seedu.duke.command;

import java.util.Hashtable;

public class EmptyCommand extends Command {

    public EmptyCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
    }
}
