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

public class CreateSprintCommandTest extends SprintCommandTest {

    @Test
    void createSprintCommand_validCommand_firstSprint() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator()
                + "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(1, projectManager.getProject(2).getSprintList().size());
        assertEquals(LocalDate.now(),
                projectManager.getProject(2).getSprintList().getSprint(1).getStartDate());
    }

    @Test
    void createSprintCommand_validCommand_firstSprint_withStartTag() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        parameters.put("start", "20001010");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator()
                + "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator()
                + "Project period: 2000-10-10 to 2000-11-28" + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: 2000-10-10 - 2000-10-19]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "=================================================================" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(1, projectManager.getProject(2).getSprintList().size());
        assertEquals(LocalDate.of(2000, 10, 10),
                projectManager.getProject(2).getSprintList().getSprint(1).getStartDate());
    }

    @Test
    void createSprintCommand_validCommand_SubsequentSprint() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);
        command.execute();
        command.execute();

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator()
                + "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator()
                + "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator()
                + "[Project ID: " + projectManager.size() + "]" + System.lineSeparator()
                + "Not first sprint: New sprint will start right after previous sprint ends." + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 2]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]"
                + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "=================================================================" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(2, projectManager.getProject(2).getSprintList().size());
        assertEquals(LocalDate.now().plusDays(10),
                projectManager.getProject(2).getSprintList().getSprint(2).getStartDate());
    }

    @Test
    void createSprintCommand_validCommand_SubsequentSprint_withStartTag() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);
        command.execute();
        parameters.put("start", "20001010");
        command = new CreateSprintCommand(parameters, projectManager);
        command.execute();

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator()
                + "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator()
                + "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator()
                + "[Project ID: " + projectManager.size() + "]" + System.lineSeparator()
                + "Not first sprint: Start tag will be ignored and new sprint will start right after "
                + "previous sprint ends." + System.lineSeparator()
                + "============================ SPRINT =============================" + System.lineSeparator()
                + "[ID: 2]" + System.lineSeparator()
                + "[Goal: Fly]" + System.lineSeparator()
                + "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]"
                + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "=================================================================" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(2, projectManager.getProject(2).getSprintList().size());
        assertEquals(LocalDate.now().plusDays(10),
                projectManager.getProject(2).getSprintList().getSprint(2).getStartDate());
    }

    @Test
    void createSprintCommand_invalidCommand_nonExistentProject() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        parameters.put("project", "99");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);
        command.execute();

        String expected = "Project not found: 99" + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals(0, projectManager.getProject(2).getSprintList().size());
    }
}
