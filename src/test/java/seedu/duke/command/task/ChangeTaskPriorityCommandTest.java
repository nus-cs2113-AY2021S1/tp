package seedu.duke.command.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeTaskPriorityCommandTest {

    @Test
    void changeTaskPriorityCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title", "description", 3, 3);

        Hashtable<String, String> setupParameters = new Hashtable<>();
        setupParameters.put("title", "a");
        setupParameters.put("desc", "b");
        setupParameters.put("priority", "high");

        new AddTaskCommand(setupParameters, projectList).execute();

        String setupPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("High priority", setupPriority);


        //Test
        Hashtable<String, String> parameters = new Hashtable<>();

        //Tests multiple random uppercase and lowercase combinations
        parameters.put("priority", "medIum");
        new ChangeTaskPriorityCommand(parameters, projectList);

        String outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);

        parameters.put("priority", "lOW");
        new ChangeTaskPriorityCommand(parameters, projectList);

        outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("Low priority", outputPriority);

        parameters.put("priority", "MEDIUM");
        new ChangeTaskPriorityCommand(parameters, projectList);

        outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);

        parameters.put("priority", "higH");
        new ChangeTaskPriorityCommand(parameters, projectList);

        outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("High priority", outputPriority);

        parameters.put("priority", "Medium");
        new ChangeTaskPriorityCommand(parameters, projectList);

        outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("Medium priority", outputPriority);
    }

    @Test
    void changeTaskPriorityCommand_invalidPriority_taskPriorityNotChanged() {
        //Setup
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title", "description", 3, 3);

        Hashtable<String, String> setupParameters = new Hashtable<>();
        setupParameters.put("title", "a");
        setupParameters.put("desc", "b");
        setupParameters.put("priority", "high");

        new AddTaskCommand(setupParameters, projectList).execute();

        String setupPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("High priority", setupPriority);

        //Test
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("priority", "very high");
        new ChangeTaskPriorityCommand(parameters, projectList);

        String outputPriority = projectList.getSelectedProject().getTaskList()
                .getTask(1).getPriority();
        assertEquals("High priority", outputPriority);
    }
}
