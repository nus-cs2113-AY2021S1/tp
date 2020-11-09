package com.scrumptious.parser;

import org.junit.jupiter.api.Test;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectParserTest {

    ProjectParser projectParser = new ProjectParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingParameters_returnsScrumptiousException() {
        String create = "create";
        assertThrows(ScrumptiousException.class, () ->
            projectParser.parseMultipleCommandsExceptions(parameters, create, projectManager));
    }

}