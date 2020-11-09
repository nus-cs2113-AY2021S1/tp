package com.scrumptious.parser;

import org.junit.jupiter.api.Test;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HelpParserTest {

    HelpParser helpParser = new HelpParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_helpActionIsNotAnInteger_returnsDukeException() {
        String notNumber = "not number";
        assertThrows(ScrumptiousException.class, () ->
            helpParser.parseMultipleCommandsExceptions(parameters, notNumber, projectManager));
    }

    @Test
    void parseMultipleCommandsExceptions_helpNumberIsNotinList_returnsDukeException() {
        String number = "6";
        assertThrows(ScrumptiousException.class, () ->
            helpParser.parseMultipleCommandsExceptions(parameters, number, projectManager));
    }
}