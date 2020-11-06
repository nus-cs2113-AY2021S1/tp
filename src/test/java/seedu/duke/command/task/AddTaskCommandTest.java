package seedu.duke.command.task;

import org.junit.jupiter.api.Test;

import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddTaskCommandTest {

    @Test
    void addTaskCommand_validCommand_returnsNormalBehavior() {
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title","description",3,3);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("title", "a");
        parameters.put("desc", "b");
        parameters.put("priority", "high");

        new AddTaskCommand(parameters, projectList).execute();

        String outputDescription = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDescription();

        String outputTitle = projectList.getSelectedProject().getBacklog()
                .getTask(1).getTitle();

        String outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();

        assertEquals("a", outputTitle);
        assertEquals("b", outputDescription);
        assertEquals("High priority", outputPriority);

        boolean outputDone = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDone();

        assertFalse(outputDone);
    }

    @Test
    void addTaskCommand_invalidPriority_noTaskCreated() {
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title", "description", 3, 3);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("title", "a");
        parameters.put("desc", "b");
        parameters.put("priority", "CRITICAL");

        new AddTaskCommand(parameters, projectList).execute();

        Task task = projectList.getSelectedProject().getBacklog().getTask(1);
        assertNull(task);
    }

    @Test
    void addTaskCommand_duplicateTask_noTaskCreated() {
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title", "description", 3, 3);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("title", "a");
        parameters.put("desc", "b");
        parameters.put("priority", "high");

        new AddTaskCommand(parameters, projectList).execute();
        new AddTaskCommand(parameters, projectList).execute();

        Task task = projectList.getSelectedProject().getBacklog().getTask(2);
        assertNull(task);
    }
}
