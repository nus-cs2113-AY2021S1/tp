package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;

/**
 * Show the progress of todos and deadlines to the user.
 */
public class PrintProgressCommand extends Command {
    public PrintProgressCommand(String userInput) {
        super(userInput);
    }

    /**
     * Show the progress of todos and deadlines to the user.
     *
     * @param calendarList the calendar list of all tasks and events
     * @param storage      not required
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        int numTotal = calculateNumTotal(calendarList);
        int numFinished = calculateNumFinished(calendarList);
        Ui.printProgress(numTotal, numFinished);
    }

    /**
     * Find the number of finished tasks in the list.
     *
     * @param calendarList the list of all calendar items.
     * @return integer of number of finished tasks.
     */
    public int calculateNumFinished(CalendarList calendarList) {
        int numFinished = 0;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }
            if (((Task) item).getIsDone()) {
                numFinished++;
            }
        }
        return numFinished;
    }

    /**
     * Find the number of total tasks in the list.
     *
     * @param calendarList the list of all calendar items.
     * @return integer of number of total tasks.
     */
    public int calculateNumTotal(CalendarList calendarList) {
        int numTotal = 0;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }
            numTotal++;
        }
        return numTotal;
    }

}
