package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.InvalidCommand;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageParserTest {

    StorageParser storageParser = new StorageParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_invalidCommands_returnsInvalidCommand() {
        String invalidCommand = "invalid command";
        Command command = storageParser.parseMultipleCommandsExceptions(parameters, invalidCommand, projectManager);
        assertTrue(command instanceof InvalidCommand);
    }
}