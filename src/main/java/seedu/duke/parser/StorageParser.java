package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.storage.ClearStorageCommand;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CLEAR;

public class StorageParser implements ExceptionsParser {
    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager) {
        switch (action.toLowerCase()) {
        case CLEAR:
            return new ClearStorageCommand(parameters, projectListManager);
        default:
            return new InvalidCommand(parameters);
        }
    }
}
