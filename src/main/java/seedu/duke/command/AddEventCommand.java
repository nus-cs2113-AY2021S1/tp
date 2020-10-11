package seedu.duke.command;

import seedu.duke.data.timetable.Event;
import seedu.duke.ui.InterfaceManager;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Adds an Event into a timetable.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Adds an event to the timetable. Parameters:"
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + PREFIX_DELIMITER + PREFIX_TIMING + " TIMING "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING + " Frequency (daily, weekly, monthly, annually) " + "]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND + " [Days before (Default: 1)] " + "]";

    private final Event event;

    /**
     * Constructor for the AddEventCommand which specifies the event to add.
     *
     * @param e Event to add.
     */
    public AddEventCommand(Event e) {
        event = e;
    }

    /**
     * Provides how the command should be used.
     *
     * @return How the command should be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        timetable.addEvent(event);
        return "Added the following!" + InterfaceManager.LS + InterfaceManager.LS + event.toString();
    }
}
