package seedu.duke.command.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeTaskPriorityCommandTest extends CommandTest {

    @Test
    void changeTaskPriorityCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ProjectManager projectList = addTestProject();

        Hashtable<String, String> setupParameters = addDefaultUserInput();

        new AddTaskCommand(setupParameters, projectList).execute();

        String setupPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("High priority", setupPriority);


        //Test
        Hashtable<String, String> parameters = new Hashtable<>();

        //Tests multiple random uppercase and lowercase combinations
        parameters.put("id","1");
        parameters.put("priority", "medIum");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();
        String outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);

        parameters.put("priority", "lOW");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();
        outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("Low priority", outputPriority);

        parameters.put("priority", "MEDIUM");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();
        outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);

        parameters.put("priority", "higH");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();
        outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("High priority", outputPriority);

        parameters.put("priority", "Medium");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();
        outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);
    }

    @Test
    void changeTaskPriorityCommand_invalidPriority_taskPriorityNotChanged() {
        //Setup
        ProjectManager projectList = addTestProject();

        Hashtable<String, String> setupParameters = addDefaultUserInput();

        new AddTaskCommand(setupParameters, projectList).execute();

        String setupPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("High priority", setupPriority);

        //Test
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("id","1");
        parameters.put("priority", "very high");
        new ChangeTaskPriorityCommand(parameters, projectList).execute();

        String outputPriority = projectList.getSelectedProject().getBacklog()
                .getTask(1).getPriority();
        assertEquals("High priority", outputPriority);
    }
}
