package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Sets the task of task number specified by the user as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Sets the task of task number specified by the user as done.
     * Saves the updated task list in the storage after the task is marked as done.
     *
     * @param taskList the task list that contains the task.
     * @param storage  the storage to be saved to.
     * @throws DukeException if the done command is invalid.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        int taskNumberCompleted;

        try {
            taskNumberCompleted = Integer.parseInt(userInput.replace("done", "").trim());
        } catch (Exception e) {
            throw new DukeException("done");
        }

        if (taskNumberCompleted > taskList.getTotalTask() || taskNumberCompleted <= 0) {
            throw new DukeException("invalid task action");
        }

        taskList.markTaskAsDone(taskNumberCompleted);

        Ui.printCompleteTaskMessage(taskNumberCompleted, taskList);

        storage.writeToFile(taskList);
    }
}
