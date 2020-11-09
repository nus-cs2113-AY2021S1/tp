package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Lists all event type of tasks (such as lecture, lab, tutorial and events)
 * in the calendar list to the user.
 */
public class PrintEventsCommand extends Command {
    public PrintEventsCommand(String userInput) {
        super(userInput);
    }

    /**
     * Lists all event (such as lecture, lab, tutorial, exam and activities)
     * in the calendar list to the user.
     *
     * @param calendarList the calendar list to list from.
     * @param storage      not required.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        Ui.printEventsListView(calendarList);
    }
}
