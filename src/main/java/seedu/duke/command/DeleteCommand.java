package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Deletes the task of task number specified by the user.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Deletes the task of task number specified by the user.
     * Saves the updated calendar list in the storage after the task is deleted.
     *
     * @param calendarList the calendar list to delete the task from.
     * @param storage      the storage to be saved to.
     * @throws DukeException if the delete command input is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        int taskNumberDelete;

        try {
            taskNumberDelete = Integer.parseInt(userInput.replace("delete", "").trim());
        } catch (Exception e) {
            throw new DukeException("delete");
        }
        if (taskNumberDelete > calendarList.getTotalTasks() || taskNumberDelete <= 0) {
            throw new DukeException("invalid task action");
        }

        Ui.printDeleteTaskMessage(taskNumberDelete, calendarList);

        calendarList.deleteTask(taskNumberDelete - 1); // - 1 to cater for index starting from 0
        storage.writeToFile(calendarList);

    }
}
