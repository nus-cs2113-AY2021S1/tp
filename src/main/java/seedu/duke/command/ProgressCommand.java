package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Show the progress of todos and deadlines to the user.
 */
public class ProgressCommand extends Command {
    public ProgressCommand(String userInput) {
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
        Ui.printProgress(calendarList);
    }
}
