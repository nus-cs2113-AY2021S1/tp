package com.scrumptious.command.sprint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSprintCommandTest extends SprintCommandTest {

    @Test
    void viewSprintTaskCommand_validCommand() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        ViewSprintCommand command = new ViewSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "========================= CURRENT SPRINT ========================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: project2Sprint1]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[Remaining: 9 days]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
    }

    @Test
    void viewSprintTaskCommand_validCommand_specifySprint() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "2");
        ViewSprintCommand command = new ViewSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 2]" + System.lineSeparator()
                + "[Goal: project2Sprint2]" + System.lineSeparator()
                + "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]"
                + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
    }

    @Test
    void viewSprintTaskCommand_validCommand_specifyProject() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("project", "1");
        parameters.put("sprint", "2");
        ViewSprintCommand command = new ViewSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: 1]" + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 2]" + System.lineSeparator()
                + "[Goal: project1Sprint2]" + System.lineSeparator()
                + "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]"
                + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
    }

    @Test
    void viewSprintTaskCommand_invalidCommand_lowerBoundSprint() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "0");
        ViewSprintCommand command = new ViewSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "Sprint not found: 0" + System.lineSeparator();
        assertEquals(expected, getOutput());
    }

    @Test
    void viewSprintTaskCommand_invalidCommand_upperBoundSprint() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "6");
        ViewSprintCommand command = new ViewSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "Sprint not found: 6" + System.lineSeparator();
        assertEquals(expected, getOutput());
    }
}
