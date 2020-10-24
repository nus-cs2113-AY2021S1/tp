package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class InvalidCommand extends Command {

    /**
     * Creates a new abstract command.
     *
     * @param parameters
     */
    public InvalidCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
        Ui.showToUser("Invalid action");
    }
}
