package seedu.duke.command.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoneTaskCommandTest extends CommandTest {
    @Test
    void doneTaskCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ProjectManager projectList = addTestProject();

        Hashtable<String, String> setupParameters = addDefaultUserInput();

        new AddTaskCommand(setupParameters, projectList).execute();

        boolean isSetupDone = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDone();

        assertFalse(isSetupDone);

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");
        new DoneTaskCommand(parameters, projectList).execute();

        boolean isDone = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDone();

        assertTrue(isDone);
    }

    @Test
    void doneTaskCommand_invalidCommand_taskNotMarkedDone() {
        //Setup
        ProjectManager projectList = addTestProject();

        Hashtable<String, String> setupParameters = addDefaultUserInput();

        new AddTaskCommand(setupParameters, projectList).execute();

        boolean isSetupDone = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDone();

        assertFalse(isSetupDone);

        Hashtable<String, String> parameters = new Hashtable<>();
        //Simulates where user enters /done 0
        parameters.put("0", "0");
        new DoneTaskCommand(parameters, projectList).execute();

        boolean isDone = projectList.getSelectedProject().getBacklog()
                .getTask(1).getDone();

        assertFalse(isDone);
    }
}
