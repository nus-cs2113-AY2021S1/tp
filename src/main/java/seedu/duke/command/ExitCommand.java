package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Saves the current calendar list locally before terminating the programme.
 */
public class ExitCommand extends Command {

    public ExitCommand(String userInput) {
        super(userInput);
    }

    /**
     * Saves the current calendar list locally.
     *
     * @param calendarList the calendar list to be saved.
     * @param storage      the storage to be saved to.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        storage.writeToFile(calendarList);
        Ui.printExitMessage();
    }

    /**
     * Sets the isExit flag to true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
