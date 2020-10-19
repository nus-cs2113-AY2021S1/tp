package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.command.task.TaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskCommandTest {

    private TaskCommand test = new TaskCommand();

    @Test
    void addTaskCommand_validCommand_returnsNormalBehavior() {
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");

        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }

        String outputDescription = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getDescription();
        String outputTitle = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getTitle();
        String outputPriority = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getPriority();

        assertEquals("a", outputTitle);
        assertEquals("b", outputDescription);
        assertEquals("High priority", outputPriority);
        boolean outputDone = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getDone();
        assertFalse(outputDone);
    }

    @Test
    void addTaskCommand_invalidPriority_throwsException() {
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "CRITICAL");

        assertThrows(DukeException.class, () -> {
            test.addTaskCommand(tasks, projectList);
        });
    }

    @Test
    void deleteTaskCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");

        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }
        //Test
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("1");
        test.deleteTaskCommand(numbers, projectList);
        assertNull(proj.getProjectBacklog().getTask(proj.getProjectBacklog().getNextId() - 1));

    }

    @Test
    void viewTaskCommand_nonNumberInput_throwsException() {
        //Setup
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");

        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }

        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("1");

        test.viewTaskCommand(numbers, projectList);


    }

    @Test
    void changeTaskPriorityCommand_validCommand_returnsNormalBehavior() {
        //Setup
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");
        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }
        //Test
        String outputPriority = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getPriority();
        assertEquals("High priority", outputPriority);
        tasks.put("id","1");
        tasks.put("priority","MEDIUM");
        System.out.println(tasks.get("priority"));
        System.out.println(tasks.get("id"));
        try {
            test.changeTaskPriorityCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }
        outputPriority = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getPriority();
        assertEquals("Medium priority", outputPriority);
        tasks.put("priority","LOW");
        try {
            test.changeTaskPriorityCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }
        outputPriority = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getPriority();
        assertEquals("Low priority", outputPriority);
    }

    @Test
    void changeTaskPriorityCommand_invalidPriority_throwsException() {
        //Setup
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");
        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }
        //Test
        String outputPriority = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getPriority();
        assertEquals("High priority", outputPriority);
        tasks.put("id","1");
        tasks.put("priority","CRITICAL");
        System.out.println(tasks.get("priority"));
        System.out.println(tasks.get("id"));

        assertThrows(DukeException.class, () -> {
            test.changeTaskPriorityCommand(tasks, projectList);
        });

    }

    @Test
    void doneTaskCommand_validCommand_returnsNormalBehavior() {
        //setup
        ArrayList<Project> projectList = new ArrayList<>(10);
        Project proj = new Project("title", "description", "3", "3");
        projectList.add(proj);
        Hashtable<String, String> tasks = new Hashtable<>();
        tasks.put("title", "a");
        tasks.put("desc", "b");
        tasks.put("priority", "HIGH");

        try {
            test.addTaskCommand(tasks, projectList);
        } catch (DukeException e) {
            System.out.println();
        }

        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("1");
        test.doneTaskCommand(numbers, projectList);

        boolean outputDone = proj.getProjectBacklog()
                .getTask(proj.getProjectBacklog().getNextId() - 1).getDone();
        assertTrue(outputDone);
    }
}