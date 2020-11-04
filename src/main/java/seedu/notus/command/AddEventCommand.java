package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.io.IOException;

import static seedu.notus.util.CommandMessage.ADD_EVENT_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;

//@@author brandonywl
/**
 * This class allows us to store relevant information regarding the events to add to the timetable and manipulate it
 * before executing it via the execute method.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    private Event event;

    /**
     * Constructor that takes in the event to be written to the timetable.
     *
     * @param event Event to be written to the timetable.
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute() {
        timetable.addEvent(event);

        try {
            storageManager.saveTimetable();
        } catch (IOException exception) {
            return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        }

        return Formatter.formatEventString(ADD_EVENT_SUCCESSFUL_MESSAGE, event);
    }
}
