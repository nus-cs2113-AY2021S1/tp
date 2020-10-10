package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Lists all tasks in the calendar list to the user.
 */
public class PrintListCommand extends Command {
    public PrintListCommand(String userInput) {
        super(userInput);
    }

    /**
     * Lists all tasks in the calendar list to the user.
     *
     * @param calendarList the calendar list to list from.
     * @param storage      not required.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        Ui.printTaskListView(calendarList);
    }
}
