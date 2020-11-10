package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.DELETE_EVENT_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.DELETE_EVENT_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;

//@@author brandonywl
/**
 * This class allows us to store relevant information regarding the events to be deleted from the timetable and
 * manipulate it before executing it via the execute method.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-e";

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
        ArrayList<String> returnMessages;
        if (index < 0 || index >= timetable.getEvents().size()) {
            returnMessages = new ArrayList<>();
            returnMessages.add(DELETE_EVENT_UNSUCCESSFUL_MESSAGE);
            returnMessages.add(INDEX_OUT_OF_RANGE_MESSAGE);
            return Formatter.formatString(returnMessages, true);
        }
        Event event = timetable.getEvent(index);
        timetable.deleteEvent(index);

        try {
            storageManager.saveTimetable();
        } catch (IOException exception) {
            return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        }

        return Formatter.formatEventString(DELETE_EVENT_SUCCESSFUL_MESSAGE, event);
    }
}
