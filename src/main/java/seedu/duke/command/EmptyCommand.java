package seedu.duke.command;

import java.util.Hashtable;

public class EmptyCommand extends Command {

    public EmptyCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
    }
}
