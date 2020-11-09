package com.scrumptious.command.sprint;

import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.sprint.Sprint;
import com.scrumptious.model.task.Task;
import com.scrumptious.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

public abstract class SprintCommandTest {

    public SprintCommandTest() {

    }
    protected final PrintStream systemOut = System.out;
    protected ByteArrayOutputStream testOut;

    @BeforeEach
    protected void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        Ui.setOutStream(new PrintStream(testOut));
    }

    @AfterEach
    protected void restoreSystemOutput() throws IOException {
        testOut.close();
        Ui.setOutStream(systemOut);
    }

    protected String getOutput() {
        return testOut.toString();
    }

    protected ProjectManager generateProject() {
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject("project1", "project1", 50, 10);
        projectManager.addProject("project2", "project2", 50, 10);
        assert projectManager.getProjectList().size() == 2 : "Dummy projects not added!";
        return projectManager;
    }

    protected void generateDummyTask(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            project.getBacklog().addTask(project.getTitle() + "task1", "task1", "HIGH");
            project.getBacklog().addTask(project.getTitle() + "task2", "task2", "MEDIUM");
            project.getBacklog().addTask(project.getTitle() + "task3", "task3", "LOW");
            assert project.getBacklog().size() == 3 : "Dummy tasks for " + project.getTitle() + " not added!";
        }
    }

    protected void generateDummyMember(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            project.getMemberList().addMember(new Member(project.getTitle() + "member1"));
            project.getMemberList().addMember(new Member(project.getTitle() + "member2"));
            project.getMemberList().addMember(new Member(project.getTitle() + "member3"));
            assert project.getMemberList().size() == 3 : "Dummy members for " + project.getTitle() + " not added!";
        }
    }

    protected void generateDummySprint(ProjectManager projectManager) {
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

    protected void generateDummySprintTask(ProjectManager projectManager) {
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

    protected void generateDummyAllocatedTask(ProjectManager projectManager) {
        for (Project project : projectManager.getProjectList().values()) {
            for (Member mem : project.getMemberList().getAllMembers()) {
                mem.allocateTask(1);
                project.getBacklog().getTask(1).allocateToMember(mem.getUserId());
                mem.allocateTask(2);
                project.getBacklog().getTask(2).allocateToMember(mem.getUserId());
                mem.allocateTask(3);
                project.getBacklog().getTask(3).allocateToMember(mem.getUserId());

                assert mem.getTaskList().size() == 3 : "Dummy allocated tasks for " + mem.getUserId() + " not added!";
            }
            for (Task task : project.getBacklog().getTaskList()) {
                assert task.getMemberList().size() == 3 : "Dummy allocated tasks not added!";
            }
        }
    }
}
