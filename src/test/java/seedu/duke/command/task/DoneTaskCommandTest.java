package seedu.duke.command.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoneTaskCommandTest {
    @Test
    void doneTaskCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title","description",3,3);

        Hashtable<String, String> setupParameters = new Hashtable<>();
        setupParameters.put("title", "a");
        setupParameters.put("desc", "b");
        setupParameters.put("priority", "high");

        new AddTaskCommand(setupParameters, projectList).execute();

        boolean isSetupDone = projectList.getSelectedProject().getTaskList()
                .getTask(1).getDone();

        assertFalse(isSetupDone);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");
        new DoneTaskCommand(parameters, projectList).execute();

        boolean isDone = projectList.getSelectedProject().getTaskList()
                .getTask(1).getDone();

        assertTrue(isDone);
    }

    @Test
    void doneTaskCommand_invalidCommand_taskNotMarkedDone() {
        //Setup
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title","description",3,3);

        Hashtable<String, String> setupParameters = new Hashtable<>();
        setupParameters.put("title", "a");
        setupParameters.put("desc", "b");
        setupParameters.put("priority", "high");

        new AddTaskCommand(setupParameters, projectList).execute();

        boolean isSetupDone = projectList.getSelectedProject().getTaskList()
                .getTask(1).getDone();

        assertFalse(isSetupDone);

        Hashtable<String, String> parameters = new Hashtable<>();
        //Simulates where user enters /done 0
        parameters.put("0", "0");
        new DoneTaskCommand(parameters, projectList).execute();

        boolean isDone = projectList.getSelectedProject().getTaskList()
                .getTask(1).getDone();

        assertFalse(isDone);
    }
}
