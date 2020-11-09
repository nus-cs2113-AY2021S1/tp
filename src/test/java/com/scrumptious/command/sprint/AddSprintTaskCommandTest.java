package com.scrumptious.command.sprint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.project.Project;
import com.scrumptious.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddSprintTaskCommandTest  extends SprintCommandTest {

    @Test
    void addSprintTaskCommand_validCommand() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");
        parameters.put("1", "2");
        parameters.put("2", "3");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "\tproject2task3 added to sprint 1." + System.lineSeparator()
                + "\tproject2task2 added to sprint 1." + System.lineSeparator()
                + "\tproject2task1 added to sprint 1." + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(3,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }

    @Test
    void addSprintTaskCommand_validCommand_withTaskTag() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("task", "1 2 3");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "\tproject2task1 added to sprint 1." + System.lineSeparator()
                + "\tproject2task2 added to sprint 1." + System.lineSeparator()
                + "\tproject2task3 added to sprint 1." + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(3,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }

    @Test
    void addSprintTaskCommand_validCommand_duplicateTask() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");
        parameters.put("1", "1");
        parameters.put("2", "1");
        parameters.put("3", "2");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "\tproject2task2 added to sprint 1." + System.lineSeparator()
                + "\tproject2task1 added to sprint 1." + System.lineSeparator()
                + "\tproject2task1 is already added in sprint 1." + System.lineSeparator()
                + "\tproject2task1 is already added in sprint 1." + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(2,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }

    @Test
    void addSprintTaskCommand_invalidCommand_nonExistentTask() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "99");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "Task not found in backlog: 99" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(0,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }

    @Test
    void addSprintTaskCommand_invalidCommand_nonExistentProject() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("project", "99");
        parameters.put("task", "1 2 3");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "Project not found: 99" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(0,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }

    @Test
    void addSprintTaskCommand_invalidCommand_nonExistentSprint() {
        ProjectManager projectManager = generateProject();
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("sprint", "99");
        parameters.put("task", "1 2 3");
        AddSprintTaskCommand command = new AddSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "Sprint not found: 99" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(0,
                projectManager.getProject(2).getSprintList().getSprint(1).getAllSprintTaskIds().size());
    }
}