package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;
import athena.exceptions.command.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests methods of the edit command.
 */
class EditCommandTest {
    private TaskList taskList;
    private TaskList editedTaskList;
    private AthenaUi athenaUi;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing.
     */
    public static TaskList getTaskList() throws CommandException {
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
     * Creates a task list that is same as getTaskList() but task number 2 is edited.
     *
     * @return TaskList for testing with an edited task number 2.
     */
    public static TaskList getEditedTaskList() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1,"Homework 2", "0800", "4", "02-01-2021", "10-12-2020",
                Importance.HIGH, "Very easy homework", false);
        taskList.addTask(2,"Assignment 3", "1600", "2", "01-01-2021", "14-12-2020",
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
        editedTaskList = getEditedTaskList();
    }

    /**
     * Tests that the details of a task is edited if a valid task number is given.
     *
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list
     */
    @Test
    public void execute_validNumber_taskIsEdited() throws CommandException {
        assertEditingSuccessful(1, taskList, editedTaskList);
    }

    /**
     * Tests that a TaskNotFoundException is thrown when an task number not in the list is given.
     */
    @Test
    public void execute_invalidNumber_taskListIsUnchanged() {
        assertEditingFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new edit command.
     *
     * @param taskNumber Task number of the task that we want to edit
     */
    private EditCommand createEditCommand(int taskNumber) {
        EditCommand command = new EditCommand(taskNumber, "Homework 2", "0800", "4", "02-01-2021", "10-12-2020",
                Importance.HIGH, "Very easy homework");
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     *
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list
     */
    private void assertCommandBehaviour(EditCommand editCommand, TaskList expectedTaskList,
                                        TaskList actualTaskList) throws CommandException {
        AthenaUi athenaUi = new AthenaUi();
        editCommand.execute(taskList, athenaUi);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts that nothing changes when the task with the given number does not exist in the given task list.
     *
     * @param taskNumber Task number to edit, but it should be an invalid number
     * @param taskList   TaskList to edit
     */
    private void assertEditingFailsDueToInvalidNumber(int taskNumber, TaskList taskList) {
        EditCommand command = createEditCommand(taskNumber);
        assertThrows(TaskNotFoundException.class, () -> {
            command.execute(taskList, athenaUi);
        });
    }

    /**
     * Asserts the task with the specified number can be successfully edited.
     *
     * @param taskNumber     Task number of the task to edit
     * @param taskList       TaskList to edit
     * @param editedTaskList Reference taskList to compare with after deleting the task
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list
     */
    private void assertEditingSuccessful(int taskNumber, TaskList taskList, TaskList editedTaskList)
            throws CommandException {
        TaskList expectedTaskList = editedTaskList;
        TaskList actualTaskList = taskList;

        EditCommand command = createEditCommand(taskNumber);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}