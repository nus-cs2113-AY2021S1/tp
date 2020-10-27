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

        try {
            if (userInput.startsWith("-t")) {
                numberDelete = Integer.parseInt(userInput.replace("-t", "").trim());
                isTask = true;
            } else if (userInput.startsWith("-e")) {
                numberDelete = Integer.parseInt(userInput.replace("-e", "").trim());
            } else {
                throw new Exception("e");
            }
        } catch (Exception e) {
            throw new DukeException("delete");
        }

        if (isTask) {
            deleteTask(calendarList, numberDelete);

        } else {
            deleteEvent(calendarList, numberDelete);
        }

        storage.writeToFile(calendarList);

    }

    /**
     * Deletes the event of event number specified by the user.
     *
     * @param calendarList the calendar list to delete the event from.
     * @param numberDelete the delete event number specified by the user.
     * @throws DukeException if the delete command input is invalid.
     */
    private void deleteEvent(CalendarList calendarList, int numberDelete) throws DukeException {
        int calendarNumber;
        calendarNumber = CalendarList.convertEventNumberToCalendarNumber(numberDelete, calendarList);
        assert calendarNumber >= 0;
        Ui.printDeleteMessage(calendarNumber, calendarList);
        calendarList.deleteEvent(calendarNumber);
    }

    /**
     * Deletes the task of task number specified by the user.
     *
     * @param calendarList the calendar list to delete the task from.
     * @param numberDelete the delete task number specified by the user.
     * @throws DukeException if the delete command input is invalid.
     */
    private void deleteTask(CalendarList calendarList, int numberDelete) throws DukeException {
        int calendarNumber;
        calendarNumber = CalendarList.convertTaskNumberToCalendarNumber(numberDelete, calendarList);
        assert calendarNumber >= 0;

        Ui.printDeleteMessage(calendarNumber, calendarList);
        calendarList.deleteTask(calendarNumber);
        Ui.printTotalTaskNumber(calendarList);

    }
}
