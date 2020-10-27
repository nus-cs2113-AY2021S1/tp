package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Prioritize the task as important.
 */
public class PrioritizeCommand extends Command {
    public PrioritizeCommand(String userInput) {
        super(userInput);
    }

    /**
     * Mark the task as important.
     *
     * @param calendarList the list of user events and tasks.
     * @param storage      the storage to be saved to.
     * @throws DukeException if the command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        int index;
        try {
            if (userInput.startsWith("*t")) {
                index = Integer.parseInt(userInput.replace("*t", "").trim());
            } else {
                throw new DukeException("prioritize");
            }
        } catch (Exception e) {
            throw new DukeException("prioritize");
        }
        markAsImportant(calendarList, index);
    }

    /**
     * Mark the task with index as important.
     *
     * @param calendarList the list of user events and tasks.
     * @param indexOfTask  the index of the task in the list.
     * @throws DukeException if the index is invalid.
     */
    public void markAsImportant(CalendarList calendarList, int indexOfTask) throws DukeException {
        if (indexOfTask > calendarList.getTotalTasks() || indexOfTask < 0) {
            throw new DukeException("invalid task action");
        }
        int calendarNumber = CalendarList.convertTaskNumberToCalendarNumber(indexOfTask, calendarList);

        assert calendarNumber >= 0;

        calendarList.markTaskAsImportant(calendarNumber);
        Ui.printPrioritizeMessage(calendarList, calendarNumber);
    }
}
