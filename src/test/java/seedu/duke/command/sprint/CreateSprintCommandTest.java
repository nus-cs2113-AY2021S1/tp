package seedu.duke.command.sprint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.io.*;
import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSprintCommandTest {
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
            project.getBacklog().addTask(project.getTitle() +"task1", "task1", "HIGH");
            project.getBacklog().addTask(project.getTitle() +"task2", "task2", "MEDIUM");
            project.getBacklog().addTask(project.getTitle() +"task3", "task3", "LOW");
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
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint1", LocalDate.now(), LocalDate.now().plusDays(9));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint2", LocalDate.now().plusDays(10), LocalDate.now().plusDays(19));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint3", LocalDate.now().plusDays(20), LocalDate.now().plusDays(49));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint4", LocalDate.now().plusDays(30), LocalDate.now().plusDays(49));
            project.getSprintList().addSprint(project, project.getTitle() + "Sprint5", LocalDate.now().plusDays(40), LocalDate.now().plusDays(49));
            assert project.getSprintList().size() == 5 : "Dummy sprints for " + project.getTitle() + " not added!";
        }
    }

    @Test
    void createSprintCommand_validCommand_firstSprint() {
        ProjectManager projectManager = generateProject();

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "Fly");
        CreateSprintCommand command = new CreateSprintCommand(parameters, projectManager);

        command.execute();

        String expected = "[Project ID: " + projectManager.getSelectedProjectIndex() + "]" + System.lineSeparator() +
                "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator() +
                "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 1]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "================================================================="
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

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator() +
                "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator() +
                "Project period: 2000-10-10 to 2000-11-28" + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 1]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: 2000-10-10 - 2000-10-19]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "=================================================================" + System.lineSeparator();
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

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator() +
                "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator() +
                "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 1]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "================================================================="
                + System.lineSeparator()
                + "[Project ID: " + projectManager.size() + "]" + System.lineSeparator() +
                "Not first sprint: New sprint will start right after previous sprint ends." + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 2]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "=================================================================" + System.lineSeparator();
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

        String expected = "[Project ID: " + projectManager.size() + "]" + System.lineSeparator() +
                "First Sprint: Project will start along with the newly created sprint" + System.lineSeparator() +
                "Project period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(49) + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 1]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: " + LocalDate.now() + " - " + LocalDate.now().plusDays(9) + "]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "================================================================="
                + System.lineSeparator()
                + "[Project ID: " + projectManager.size() + "]" + System.lineSeparator() +
                "Not first sprint: Start tag will be ignored and new sprint will start right after "
                + "previous sprint ends." + System.lineSeparator() +
                "============================ SPRINT =============================" + System.lineSeparator() +
                "[ID: 2]" + System.lineSeparator() +
                "[Goal: Fly]" + System.lineSeparator() +
                "[Period: " + LocalDate.now().plusDays(10) + " - " + LocalDate.now().plusDays(19) + "]" + System.lineSeparator() +
                "[No allocated tasks]" + System.lineSeparator() +
                "=================================================================" + System.lineSeparator();
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

        String expected =  "Project not found: 99";
        assertEquals(expected, getOutput());
        assertEquals(0, projectManager.getProject(2).getSprintList().size());
    }
}
