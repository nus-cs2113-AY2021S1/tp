package seedu.duke.command;

import seedu.duke.CommandException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;

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
     * @throws CommandException if the command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws CommandException {
        int index;
        try {
            if (userInput.startsWith("*t")) {
                String[] command = userInput.split("\\*t", 2);
                index = Integer.parseInt(command[1].trim());
            } else {
                throw new CommandException("prioritize");
            }
        } catch (Exception e) {
            throw new CommandException("prioritize");
        }
        markAsImportant(calendarList, index);
        storage.writeToFile(calendarList);
    }

    /**
     * Mark the task with index as important.
     *
     * @param calendarList the list of user events and tasks.
     * @param indexOfTask  the index of the task in the list.
     * @throws CommandException if the index is invalid.
     */
    public void markAsImportant(CalendarList calendarList, int indexOfTask) throws CommandException {
        if (indexOfTask > calendarList.getTotalTasks() || indexOfTask < 0) {
            throw new CommandException("invalid task action");
        }
        int calendarNumber = CalendarList.convertTaskNumberToCalendarNumber(indexOfTask, calendarList);

        if (((Task) calendarList.getItem(calendarNumber)).getIsImportant()) {
            throw new CommandException("important task");
        }

        assert calendarNumber >= 0;

        calendarList.markTaskAsImportant(calendarNumber);
        Ui.printPrioritizeMessage(calendarList, calendarNumber);
    }
}
