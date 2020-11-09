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

public class EditSprintCommandTest extends SprintCommandTest {

    @Test
    void editSprintCommand_validCommand() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "New goal");
        EditSprintCommand command = new EditSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "Goal updated." + System.lineSeparator()
                + "========================= CURRENT SPRINT ========================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: New goal]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[Remaining: 9 days]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals("New goal",
                projectManager.getProject(2).getSprintList().getSprint(1).getGoal());
    }

    @Test
    void editSprintCommand_validCommand_sameGoal() {
        ProjectManager projectManager = generateProject();
        generateDummySprint(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "project2Sprint1");
        EditSprintCommand command = new EditSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "Provided goal is the same as current goal." + System.lineSeparator()
                + "========================= CURRENT SPRINT ========================" + System.lineSeparator()
                + "[ID: 1]" + System.lineSeparator()
                + "[Goal: project2Sprint1]" + System.lineSeparator()
                + "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator()
                + "[Remaining: 9 days]" + System.lineSeparator()
                + "[No allocated tasks]" + System.lineSeparator()
                + "================================================================="
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        assertEquals("project2Sprint1",
                projectManager.getProject(2).getSprintList().getSprint(1).getGoal());
    }
}
