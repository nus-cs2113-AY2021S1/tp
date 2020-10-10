package seedu.duke.command;

import seedu.duke.data.timetable.Event;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;

/**
 * This class allows us to store relevant information regarding the events to add to the timetable and manipulate it
 * before executing it via the execute method.
 */
public class AddEventCommand extends Command{

    public static final String COMMAND_WORD = "add-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Adds an event to the timetable. Parameters:"
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + PREFIX_DELIMITER + PREFIX_TIMING + " TIMING "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING + " Frequency (daily, weekly, monthly, annually) " + "]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND + " [Days before (Default: 1)] " + "]";

    private Event event;

    /**
     * Constructor that takes in the event to be written to the timetable
     * @param e Event to be written to the timetable.
     */
    public AddEventCommand(Event e) {
        event = e;
    }

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        timetable.addEvent(event);
        return "Added the following!\n\n" + event.toString();
    }
}
