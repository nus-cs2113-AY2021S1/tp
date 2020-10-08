package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

public class HelpCommand extends Command {

    public HelpCommand(String userInput) {
        super(userInput);
    }

    /**
     * Lists all available commands to the user.
     *
     * @param calendarList not required.
     * @param storage      not required.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        Ui.printHelpCommand();
    }
}
