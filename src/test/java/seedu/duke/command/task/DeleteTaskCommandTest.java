package seedu.duke.command.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteTaskCommandTest extends CommandTest {

    @Test
    void deleteTaskCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ProjectManager projectList = addTestProject();

        Hashtable<String, String> setupParameters = addDefaultUserInput();

        new AddTaskCommand(setupParameters, projectList).execute();

        String outputTitle = projectList.getSelectedProject().getBacklog()
                .getTask(1).getTitle();

        assertEquals("a", outputTitle);

        //Test
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");
        new DeleteTaskCommand(parameters, projectList).execute();

        Task task = projectList.getSelectedProject().getBacklog().getTask(1);
        assertNull(task);
    }
}
