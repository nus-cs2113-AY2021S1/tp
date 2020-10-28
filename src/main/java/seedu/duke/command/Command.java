package seedu.duke.command;

import seedu.duke.CommandException;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command and throws a CommandException when the command is invalid.
     */
    public abstract void execute(CalendarList calendarList, Storage storage) throws CommandException;

    /**
     * Returns true if the command is exit.
     */
    public boolean isExit() {
        return false;
    }

}
