package seedu.duke.command;

import seedu.duke.CommandException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;

/**
 * Sets the task of task number specified by the user as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Sets the task of task number specified by the user as done.
     * Saves the updated calendar list in the storage after the task is marked as done.
     *
     * @param calendarList the calendar list that contains the task.
     * @param storage      the storage to be saved to.
     * @throws CommandException if the done command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws CommandException {
        int taskNumberCompleted;

        try {
            String[] command = userInput.split("done", 2);
            taskNumberCompleted = Integer.parseInt(command[1].trim());
        } catch (Exception e) {
            throw new CommandException("done");
        }

        if (taskNumberCompleted > calendarList.getTotalTasks() || taskNumberCompleted <= 0) {
            throw new CommandException("invalid task action");
        }
        int calendarNumberCompleted = CalendarList.convertTaskNumberToCalendarNumber(taskNumberCompleted, calendarList);

        if (((Task) calendarList.getItem(calendarNumberCompleted)).getIsDone()) {
            throw new CommandException("task done");
        }

        assert calendarNumberCompleted >= 0;

        calendarList.markTaskAsDone(calendarNumberCompleted);
        Ui.printCompleteTaskMessage(calendarNumberCompleted, calendarList);

        storage.writeToFile(calendarList);
    }
}
