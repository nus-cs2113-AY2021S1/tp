package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.help.MemberHelpCommand;
import com.scrumptious.command.help.ProjectHelpCommand;
import com.scrumptious.command.help.SprintHelpCommand;
import com.scrumptious.command.help.StorageHelpCommand;
import com.scrumptious.command.help.TaskHelpCommand;
import com.scrumptious.exception.DukeException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

public class HelpParser implements ExceptionsParser {
    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {
        if (!ParserManager.isStringIntParsable(action)) {
            throw new DukeException("Please give me a number!");
        }
        if (Integer.parseInt(action) < 1 || Integer.parseInt(action) > 5) {
            throw new DukeException("The command number is not in the list!");
        }
        switch (action) {
        case "1":
            return new ProjectHelpCommand(parameters);
        case "2":
            return new MemberHelpCommand(parameters);
        case "3":
            return new TaskHelpCommand(parameters);
        case "4":
            return new SprintHelpCommand(parameters);
        case "5":
            return new StorageHelpCommand(parameters);
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
