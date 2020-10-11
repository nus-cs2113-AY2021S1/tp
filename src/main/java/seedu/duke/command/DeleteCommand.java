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
     * Deletes the task/event of task/event number specified by the user.
     * Saves the updated calendar list in the storage after the task is deleted.
     *
     * @param calendarList the calendar list to delete the task/event from.
     * @param storage      the storage to be saved to.
     * @throws DukeException if the delete command input is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        int numberDelete = 0;
        boolean isTask = false;
        int calendarNumber;

        try {
            if (userInput.contains("-t")) {
                numberDelete = Integer.parseInt(userInput.replace("-t", "").trim());
                isTask = true;
            } else if (userInput.contains("-e")) {
                numberDelete = Integer.parseInt(userInput.replace("-e", "").trim());
            }
        } catch (Exception e) {
            throw new DukeException("delete");
        }

        if (isTask) {
            if (numberDelete > calendarList.getTotalTasks() || numberDelete <= 0) {
                throw new DukeException("invalid task action");
            }
            calendarNumber = CalendarList.convertTaskNumberToCalendarNumber(numberDelete, calendarList);
            Ui.printDeleteMessage(calendarNumber, calendarList);
            calendarList.deleteTask(calendarNumber);
            Ui.printTotalTaskNumber(calendarList);

        } else {
            if (numberDelete > calendarList.getTotalEvents() || numberDelete <= 0) {
                throw new DukeException("invalid event action");
            }
            calendarNumber = CalendarList.convertEventNumberToCalendarNumber(numberDelete, calendarList);
            Ui.printDeleteMessage(calendarNumber, calendarList);
            calendarList.deleteEvent(numberDelete);
        }

        storage.writeToFile(calendarList);

    }
}
