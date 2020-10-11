package seedu.duke.command;

import seedu.duke.data.timetable.Event;
import seedu.duke.data.timetable.RecurringEvent;
import seedu.duke.ui.InterfaceManager;
import seedu.duke.util.DateTimeManager;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_STOP_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;

/**
 * This class allows us to store relevant information regarding the events to add to the timetable and manipulate it
 * before executing it via the execute method.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Adds an event to the timetable. Parameters:"
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + PREFIX_DELIMITER + PREFIX_TIMING + " TIMING (Format: " + DateTimeManager.DATEFORMAT + ") "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING
            + String.format(" Frequency (%s, %s, %s, %s)] ",
            RecurringEvent.DAILY_RECURRENCE_TYPE, RecurringEvent.WEEKLY_RECURRENCE_TYPE,
            RecurringEvent.MONTHLY_RECURRENCE_TYPE, RecurringEvent.YEARLY_RECURRENCE_TYPE)
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND + " [Days before (Default: 1)]" + "] "
            + "[" + PREFIX_DELIMITER + PREFIX_STOP_RECURRING + " TIMING (Format: " + DateTimeManager.DATEFORMAT + ")]";

    private final Event EVENT;

    /**
     * Constructor that takes in the event to be written to the timetable.
     *
     * @param event Event to be written to the timetable.
     */
    public AddEventCommand(Event event) {
        EVENT = event;
    }

    /**
     * Provides a description of how the command should be used.
     *
     * @return A string description of the command should be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        timetable.addEvent(EVENT);
        return "Added the following!" + InterfaceManager.LS + InterfaceManager.LS + EVENT.toString();
    }
}
