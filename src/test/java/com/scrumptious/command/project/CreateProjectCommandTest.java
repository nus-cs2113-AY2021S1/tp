package com.scrumptious.command.project;

import com.scrumptious.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.project.ProjectManager;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.scrumptious.command.CommandSummary.DESCRIPTION;
import static com.scrumptious.command.CommandSummary.SPRINT_DURATION;
import static com.scrumptious.command.CommandSummary.DURATION;
import static com.scrumptious.command.CommandSummary.TITLE;

class CreateProjectCommandTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        Ui.setOutStream(new PrintStream(testOut));
    }

    @Test
    void addProject_validInput_noError() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "testing project commands");
        parameters.put(SPRINT_DURATION, "20");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);
        command.execute();

        String expected = "Project successfully created." + System.lineSeparator()
                + "\tTitle: Test Project" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertFalse(projectManager.isEmpty());
    }

    @Test
    void addProject_validInput_returnTrue() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(SPRINT_DURATION, "20");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);
        command.execute();
        assertEquals(projectManager.size(), 1);
    }

    @Test
    void addProject_missingParameters_throwsNullPointer() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);

        assertThrows(NullPointerException.class, command::execute);
    }

    @Test
    void addProject_invalidParameters_throwsNullPointer() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");
        parameters.put(SPRINT_DURATION, "aa");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);

        assertThrows(NumberFormatException.class, command::execute);
    }

    @Test
    void addProject_zeroSprintDuration_throwsDukeException() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");
        parameters.put(SPRINT_DURATION, "0");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);
        command.execute();
        String expected = "Sprint duration cannot be zero." + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertTrue(projectManager.isEmpty());
    }

    @Test
    void addProject_durationNotMultipleOfSd_throwsDukeException() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");
        parameters.put(SPRINT_DURATION, "100");

        ProjectManager projectManager = new ProjectManager();
        CreateProjectCommand command = new CreateProjectCommand(parameters, projectManager);
        command.execute();
        String expected = "Project duration must be in multiples of Sprint intervals." + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertTrue(projectManager.isEmpty());
    }

    @AfterEach
    public void restoreSystemOutput() throws IOException {
        testOut.close();
        Ui.setOutStream(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }



}