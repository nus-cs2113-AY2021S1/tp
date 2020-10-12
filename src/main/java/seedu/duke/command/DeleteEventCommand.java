package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.timetable.Event;
import seedu.duke.ui.InterfaceManager;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;

/**
 * This class allows us to store relevant information regarding the events to be deleted from the timetable and
 * manipulate it before executing it via the execute method.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-e";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";
    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index you specified is out of range. Please specify "
            + "the index that is indicated when you print the event list";

    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Event deleted:" + InterfaceManager.LS;

    private int index;

    public DeleteEventCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        if (index < 0 || index >= timetable.getEvents().size()) {
            return INDEX_OUT_OF_RANGE_MESSAGE;
        }
        Event event = timetable.getEvent(index);
        timetable.deleteEvent(index);

        return COMMAND_SUCCESSFUL_MESSAGE + event.toString();
    }
}
