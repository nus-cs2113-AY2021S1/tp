package seedu.duke.commands;

//@@author GuoAi

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.ListType;
import seedu.duke.model.item.Task;
import seedu.duke.model.itemlist.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest extends CommandTest {

    private int testPriority = 1;
    private String testPriorityString = "p/1";
    private boolean isDone = true;

    @Test
    void execute_validTaskPriority_deleteTasksWithSamePriority() throws DukeException {
        resetFields();

        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        tasks.addTasksFromList(newTaskList);

        assertEquals(2, tasks.size());

        new DeleteCommand(testPriorityString).execute(model);

        assertEquals(0, tasks.size());
    }

    @Test
    void execute_invalidTaskPriority_throwsException() throws DukeException {
        resetFields();

        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        tasks.addTasksFromList(newTaskList);

        assertEquals(2, tasks.size());

        String inputPriorityStringNoPriority = "p/";
        assertThrows(DukeException.class, () ->
                new DeleteCommand(inputPriorityStringNoPriority).execute(model));

        String inputPriorityStringHigh = "p/2";
        assertThrows(DukeException.class, () ->
                new DeleteCommand(inputPriorityStringHigh).execute(model));

        String inputPriorityStringNegative = "p/-1";
        assertThrows(DukeException.class, () ->
                new DeleteCommand(inputPriorityStringNegative).execute(model));
    }

    @Test
    void execute_validTaskIndex_deleteIndividualTasks() throws DukeException {
        resetFields();

        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        tasks.addTasksFromList(newTaskList);

        assertEquals(1, tasks.size());

        new DeleteCommand(1).execute(model);

        assertEquals(0, tasks.size());
    }

    @Test
    void execute_outOfBoundTaskIndex_doesNotChangeTaskList() throws DukeException {
        resetFields();

        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Task(TEST_DESCRIPTION, isDone, testPriority));
        tasks.addTasksFromList(newTaskList);

        assertEquals(1, tasks.size());

        new DeleteCommand(2).execute(model);

        assertEquals(1, tasks.size());
        assertEquals(TEST_DESCRIPTION, tasks.get(0).getDescription());
        assertEquals(isDone, tasks.get(0).getIsDone());
        assertEquals(testPriority, tasks.get(0).getPriority());
    }
}
