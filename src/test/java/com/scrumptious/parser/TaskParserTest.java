package com.scrumptious.parser;

import org.junit.jupiter.api.Test;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskParserTest {

    TaskParser taskParser = new TaskParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingParameters_returnsDukeException() {
        String add = "add";
        assertThrows(ScrumptiousException.class, () ->
            taskParser.parseMultipleCommandsExceptions(parameters, add, projectManager));
    }

}