package com.scrumptious.parser;

import org.junit.jupiter.api.Test;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberParserTest {

    MemberParser memberParser = new MemberParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingName_returnsScrumptiousException() {
        String add = "add";
        assertThrows(ScrumptiousException.class, () ->
            memberParser.parseMultipleCommandsExceptions(parameters, add, projectManager));
    }
}