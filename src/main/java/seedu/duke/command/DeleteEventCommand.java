package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.timetable.Event;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;

/**
 * This class allows us to store relevant information regarding the events to be deleted from the timetable and
 * manipulate it before executing it via the execute method.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-e";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Event deleted:\n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    private int index;

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    public DeleteEventCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        Event event;
        try {
            event = timetable.deleteEvent(index);
        } catch (SystemException e) {
            return e.getMessage();
        }
        return COMMAND_SUCCESSFUL_MESSAGE + event.toString();
    }
}
