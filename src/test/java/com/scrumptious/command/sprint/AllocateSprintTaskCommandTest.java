package com.scrumptious.command.sprint;

import com.scrumptious.command.task.CommandTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.sprint.Sprint;
import com.scrumptious.model.task.Task;
import com.scrumptious.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllocateSprintTaskCommandTest extends SprintCommandTest {

    @Test
    void allocateSprintTaskCommand_validCommand() {
        ProjectManager projectManager = generateProject();
        generateDummyMember(projectManager);
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);
        generateDummySprintTask(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("user", "project2member1 project2member2 project2member3");
        parameters.put("task", "1 2 3");
        AllocateSprintTaskCommand command = new AllocateSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "[Sprint ID: 1]" + System.lineSeparator()
                + "project2task1 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator()
                + "project2task2 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator()
                + "project2task3 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        for (Member mem : projectManager.getProject(2).getMemberList().getAllMembers()) {
            assertEquals(3, mem.getTaskList().size());
        }
    }

    @Test
    void allocateSprintTaskCommand_validCommand_specifySprint() {
        ProjectManager projectManager = generateProject();
        generateDummyMember(projectManager);
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);
        generateDummySprintTask(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("user", "project2member1 project2member2 project2member3");
        parameters.put("task", "1 2 3");
        parameters.put("sprint", "3");
        AllocateSprintTaskCommand command = new AllocateSprintTaskCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator()
                + "[Sprint ID: 3]" + System.lineSeparator()
                + "project2task1 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator()
                + "project2task2 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator()
                + "project2task3 is assigned to [project2member1, project2member2, project2member3]"
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        for (Member mem : projectManager.getProject(2).getMemberList().getAllMembers()) {
            assertEquals(3, mem.getTaskList().size());
        }
    }

    @Test
    void allocateSprintTaskCommand_validCommand_specifyProject() {
        ProjectManager projectManager = generateProject();
        generateDummyMember(projectManager);
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);
        generateDummySprintTask(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("user", "project1member1 project1member2 project1member3");
        parameters.put("task", "1 2 3");
        parameters.put("sprint", "3");
        parameters.put("project", "1");
        AllocateSprintTaskCommand command = new AllocateSprintTaskCommand(parameters, projectManager);
        command.execute();

        String expected = "[Project ID: 1]" + System.lineSeparator()
                + "[Sprint ID: 3]" + System.lineSeparator()
                + "project1task1 is assigned to [project1member1, project1member2, project1member3]"
                + System.lineSeparator()
                + "project1task2 is assigned to [project1member1, project1member2, project1member3]"
                + System.lineSeparator()
                + "project1task3 is assigned to [project1member1, project1member2, project1member3]"
                + System.lineSeparator();
        assertEquals(expected, getOutput());
        for (Member mem : projectManager.getProject(1).getMemberList().getAllMembers()) {
            assertEquals(3, mem.getTaskList().size());
        }
    }

    @Test
    void allocateSprintTaskCommand_invalidCommand_nonExistentUser() {
        ProjectManager projectManager = generateProject();
        generateDummyMember(projectManager);
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);
        generateDummySprintTask(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("user", "fakeuser1 fakeuser3");
        parameters.put("task", "1 2 3");
        parameters.put("sprint", "3");
        parameters.put("project", "1");
        AllocateSprintTaskCommand command = new AllocateSprintTaskCommand(parameters, projectManager);
        command.execute();

        String expected = "User not found: fakeuser1" + System.lineSeparator();
        assertEquals(expected, getOutput());
        for (Task task : projectManager.getProject(1).getBacklog().getTaskList()) {
            assertEquals(0, task.getMemberList().size());
        }
    }

    @Test
    void allocateSprintTaskCommand_invalidCommand_nonExistentTask() {
        ProjectManager projectManager = generateProject();
        generateDummyMember(projectManager);
        generateDummyTask(projectManager);
        generateDummySprint(projectManager);
        generateDummySprintTask(projectManager);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("user", "project1member1 project1member2");
        parameters.put("task", "0");
        parameters.put("sprint", "3");
        parameters.put("project", "1");
        AllocateSprintTaskCommand command = new AllocateSprintTaskCommand(parameters, projectManager);
        command.execute();

        String expected = "Task not found in backlog: 0" + System.lineSeparator();
        assertEquals(expected, getOutput());
        for (Task task : projectManager.getProject(1).getBacklog().getTaskList()) {
            assertEquals(0, task.getMemberList().size());
        }
    }
}
