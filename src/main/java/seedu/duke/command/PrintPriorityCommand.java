package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

public class PrintPriorityCommand extends Command {
    public PrintPriorityCommand(String userInput) {
        super(userInput);
    }

    /**
     * Show all important tasks in the user's calendar list.
     *
     * @param calendarList the calendar list of all tasks and events
     * @param storage      not required
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        Ui.printImportantTasks(calendarList);
    }
}
