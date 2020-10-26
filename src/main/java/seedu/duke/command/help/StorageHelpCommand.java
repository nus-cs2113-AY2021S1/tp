package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class StorageHelpCommand extends Command {
    public StorageHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("1. Clear all data. You will be prompted to confirm the decision.");
        Ui.showToUserLn("   Data will be cleared if \"y\" (case-insensitive) is provided.");
        Ui.showToUserLn("   Format: storage /clear");
        Ui.showToUserLn("   Example: storage /clear");
    }
}
