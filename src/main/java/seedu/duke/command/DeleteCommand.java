package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.Ui;

/**
 * Deletes the task of task number specified by the user.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Deletes the task of task number specified by the user.
     *
     * @param taskList the task list to delete the task from.
     * @param storage not required.
     * @throws DukeException if the delete command input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        int taskNumberDelete;

        try {
            taskNumberDelete = Integer.parseInt(userInput.replace("delete", "").trim());
        } catch (Exception e) {
            throw new DukeException("delete");
        }
        if (taskNumberDelete > taskList.getTotalTask()) {
            throw new DukeException("invalid task action");
        }

        Ui.printDeleteTaskMessage(taskNumberDelete, taskList);

        taskList.deleteTask(taskNumberDelete - 1); // - 1 to cater for index starting from 0

    }
}
