package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.InvalidCommand;
import com.scrumptious.command.storage.ClearStorageCommand;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.CLEAR;

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
