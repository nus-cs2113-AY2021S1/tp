package seedu.ezmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.storage.Storage;
import seedu.ezmanager.task.Task;

public class StorageTest {

    public Path testFolder;

    private String filePath = "test_ezmanager.txt";
    private Storage storage = new Storage(filePath);

    @Test
    void loadData() throws EzExceptions {
        ArrayList<TeamMember> actualLoadedMembers = storage.loadTeamMembers();
        ArrayList<TeamMember> expectedMembers = new ArrayList<>();
        TeamMember onePerson = new TeamMember("one person");
        TeamMember anotherPerson = new TeamMember("another person");
        expectedMembers.add(onePerson);
        expectedMembers.add(anotherPerson);
        assertTrue(expectedMembers.size() == actualLoadedMembers.size());

        Project oneProject = new Project("one");
        oneProject.addProjectDeadline(LocalDate.parse("2020-10-20"));
        oneProject.addTeamMemberToProject(onePerson);
        oneProject.addTeamMemberToProject(anotherPerson);
        Task oneTask = new Task("one task");
        oneTask.setMember(onePerson);
        Task anotherTask = new Task("another task");
        oneProject.addTask(oneTask);
        oneProject.addTask(anotherTask);

        ArrayList<Project> actualProjects = storage.loadProjects(actualLoadedMembers);

        for (Project actualProject : actualProjects) {
            ArrayList<Task> actualTasks = actualProject.getTaskList();
            for (Task actualTask : actualTasks) {
                boolean inListOne = containsTask(actualTask.getDescription(), oneProject.getTaskList());
                assertTrue(inListOne);
            }
        }

    }

    boolean containsTask(String taskName, ArrayList<Task> tasks) {
        for (Task task : tasks) {
            if (taskName.equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

}