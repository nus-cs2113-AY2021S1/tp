package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.command.DoneInvalidIndexException;
import athena.exceptions.command.CommandException;
import athena.exceptions.command.TaskIsDoneException;
import athena.ui.AthenaUi;
import athena.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests methods of the done command.
 */
class DoneCommandTest {
    private TaskList taskList;
    private TaskList taskListWithDone;
    private AthenaUi athenaUi;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing.
     */
    private TaskList getTaskList() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "01-01-2021", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(2, "Assignment 3", "1600", "2", "01-01-2021", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    /**
     * Creates a task list that is same as getTaskList() but task number 1 is done.
     *
     * @return TaskList for testing with a done task number 1.
     */
    private TaskList getTaskListWithDone() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        Task doneTask = new Task("Assignment 2", "1600", "2", "01-01-2021",
                "13-12-2020", Importance.MEDIUM, "Tough assignment", 1, false);
        doneTask.setDone();
        taskList.addTask(doneTask);
        taskList.addTask(2, "Assignment 3", "1600", "2", "01-01-2021", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() throws CommandException {
        athenaUi = new AthenaUi();
        taskList = getTaskList();
        taskListWithDone = getTaskListWithDone();
    }

    /**
     * Tests that a task is marked as done if a valid task number is given.
     *
     * @throws DoneInvalidIndexException Exception thrown when the given task number is not in the list
     */
    @Test
    public void execute_validNumber_taskIsDone()
            throws DoneInvalidIndexException, TaskIsDoneException {
        assertDoneSuccessful(1, taskList, taskListWithDone);
    }

    /**
     * Tests that a DoneInvalidIndexException is thrown when an task number not in the list is given.
     */
    @Test
    public void execute_invalidNumber_taskListIsUnchanged() {
        assertDoneFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new done command.
     *
     * @param taskNumber Task number of the task that we want to mark as done
     */
    private DoneCommand createDoneCommand(int taskNumber) {
        DoneCommand command = new DoneCommand(taskNumber);
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     *
     * @throws DoneInvalidIndexException Exception thrown when the given task number is not in the list
     */
    private void assertCommandBehaviour(DoneCommand doneCommand, TaskList expectedTaskList, TaskList actualTaskList)
            throws DoneInvalidIndexException, TaskIsDoneException {
        AthenaUi athenaUi = new AthenaUi();
        doneCommand.execute(taskList, athenaUi);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts that nothing changes when the task with the given number does not exist in the given task list.
     *
     * @param taskNumber Task number to mark as done, but it should be an invalid number
     * @param taskList   TaskList to modify
     */
    private void assertDoneFailsDueToInvalidNumber(int taskNumber, TaskList taskList) {
        DoneCommand command = createDoneCommand(taskNumber);
        assertThrows(DoneInvalidIndexException.class, () -> {
            command.execute(taskList, athenaUi);
        });
    }

    /**
     * Asserts the task with the specified number can be successfully marked as done.
     *
     * @param taskNumber          Task number of the task to mark as done
     * @param taskList            TaskList to modify
     * @param taskListWithoutTask Reference taskList to compare with after marking the task as done
     * @throws DoneInvalidIndexException Exception thrown when the given task number is not in the list
     */
    private void assertDoneSuccessful(int taskNumber, TaskList taskList, TaskList taskListWithoutTask)
            throws DoneInvalidIndexException, TaskIsDoneException {
        TaskList expectedTaskList = taskListWithoutTask;
        TaskList actualTaskList = taskList;

        DoneCommand command = createDoneCommand(taskNumber);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}