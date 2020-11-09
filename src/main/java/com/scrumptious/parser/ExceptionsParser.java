package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.exception.ScrumptiousException;

import java.util.Hashtable;

public interface ExceptionsParser {

    /**
     * Parses the different parts of the user input commands.
     * @param parameters parameters of the command action.
     * @param action command action word.
     * @param projectListManager manager of projects
     * @return a command to be executed by SCRUMptious.
     * @throws ScrumptiousException when invalid commands or parameters are input.
     */
    Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                            ProjectManager projectListManager)
            throws ScrumptiousException;
}
