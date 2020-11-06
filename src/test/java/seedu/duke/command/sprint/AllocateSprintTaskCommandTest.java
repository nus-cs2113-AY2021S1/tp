package seedu.duke.command.sprint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllocateSprintTaskCommandTest {
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        Ui.setOutStream(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemOutput() throws IOException {
        testOut.close();
        Ui.setOutStream(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }

    private ProjectManager generateProject() {
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject("project1", "project1", 50, 10);
        projectManager.addProject("project2", "project2", 50, 10);
        assert projectManager.getProjectList().size() == 2 : "Dummy projects not added!";
        return projectManager;
    }

    private void generateDummyTask(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            project.getBacklog().addTask(project.getTitle() + "task1", "task1", "HIGH");
            project.getBacklog().addTask(project.getTitle() + "task2", "task2", "MEDIUM");
            project.getBacklog().addTask(project.getTitle() + "task3", "task3", "LOW");
            assert project.getBacklog().size() == 3 : "Dummy tasks for " + project.getTitle() + " not added!";
        }
    }

    private void generateDummyMember(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            project.getMemberList().addMember(new Member(project.getTitle() + "member1"));
            project.getMemberList().addMember(new Member(project.getTitle() + "member2"));
            project.getMemberList().addMember(new Member(project.getTitle() + "member3"));
            assert project.getMemberList().size() == 3 : "Dummy members for " + project.getTitle() + " not added!";
        }
    }

    private void generateDummySprint(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint1",
                    LocalDate.now(), LocalDate.now().plusDays(9));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint2",
                    LocalDate.now().plusDays(10), LocalDate.now().plusDays(19));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint3",
                    LocalDate.now().plusDays(20), LocalDate.now().plusDays(49));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint4",
                    LocalDate.now().plusDays(30), LocalDate.now().plusDays(49));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint5",
                    LocalDate.now().plusDays(40), LocalDate.now().plusDays(49));
            assert project.getSprintList().size() == 5 : "Dummy sprints for " + project.getTitle() + " not added!";
        }
    }

    private void generateDummySprintTask(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            for (Sprint sprint : project.getSprintList().getSprintList()) {
                sprint.addSprintTask(1);
                sprint.addSprintTask(2);
                sprint.addSprintTask(3);
                assert sprint.getAllSprintTaskIds().size() == 3 : "Dummy sprint tasks for "
                        + sprint.getGoal() + " not added!";
            }
        }

    }

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

        String expected = "User not found: fakeuser1";
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

        String expected = "Task not found in backlog: 0";
        assertEquals(expected, getOutput());
        for (Task task : projectManager.getProject(1).getBacklog().getTaskList()) {
            assertEquals(0, task.getMemberList().size());
        }
    }
}
