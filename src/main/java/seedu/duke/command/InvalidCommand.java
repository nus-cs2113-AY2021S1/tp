package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class InvalidCommand extends Command {

    public InvalidCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("Invalid action!");
    }
}
