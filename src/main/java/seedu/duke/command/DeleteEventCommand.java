package seedu.duke.command;

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

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Event deleted:" + InterfaceManager.LS;
    private static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Event failed to delete: " + InterfaceManager.LS;
    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index you specified is out of range. "
            + "Please specify the index that is indicated when you print the event list";

    private int index;

    /**
     * Constructs a DeleteEventCommand that specifies which index to delete.
     *
     * @param index Index written on list-e -1.
     */
    public DeleteEventCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        if (index < 0 || index >= timetable.getEvents().size()) {
            return COMMAND_UNSUCCESSFUL_MESSAGE + INDEX_OUT_OF_RANGE_MESSAGE;
        }
        Event event = timetable.getEvent(index);
        timetable.deleteEvent(index);
        return COMMAND_SUCCESSFUL_MESSAGE + event.toString();
    }
}
