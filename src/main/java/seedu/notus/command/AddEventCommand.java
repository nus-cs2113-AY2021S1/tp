package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.ADD_EVENT_DUPLICATE_WARNING;
import static seedu.notus.util.CommandMessage.ADD_EVENT_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.ADD_EVENT_SUCCESSFUL_WARNING;
import static seedu.notus.util.CommandMessage.ADD_EVENT_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;

//@@author brandonywl
/**
 * This class allows us to store relevant information regarding the events to add to the timetable and manipulate it
 * before executing it via the execute method.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add-e";
    public static final int DEFAULT_EVENT_LENGTH = 1;
    public static final LocalTime DEFAULT_EVENT_END_TIMING = LocalTime.of(23,59);

    private Event event;

    /**
     * Constructor that takes in the event to be written to the timetable.
     *
     * @param event Event to be written to the timetable.
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    // Only scenario for the command to fail is when endDateTime < startDateTime
    @Override
    public String execute() {
        String warnings = "";
        // End datetime can be the same as start datetime -> 1 minute event
        if (event.getEndDateTime().compareTo(event.getStartDateTime()) < 0) {
            return Formatter.formatString(ADD_EVENT_UNSUCCESSFUL_MESSAGE);
        }

        if (!event.hasSameStartEndDateDate()) {
            // Throw a warning that end date time will be adjusted to startDateTime's date @ 2359
            LocalDateTime endDateTime = event.getStartDateTime().with(DEFAULT_EVENT_END_TIMING);
            event.setEndDateTime(endDateTime);
        }

        ArrayList<Event> clashes = timetable.getClashingEvents(event);
        tagManager.rebindTags(event);
        timetable.addEvent(event);

        try {
            storageManager.saveTimetable();
        } catch (IOException exception) {
            return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        }
        String header = ADD_EVENT_SUCCESSFUL_MESSAGE;
        for (Event clashEvent : clashes) {
            if (clashEvent.equals(event)) {
                header = ADD_EVENT_DUPLICATE_WARNING;
                break;
            }
            header = ADD_EVENT_SUCCESSFUL_WARNING;
        }
        return Formatter.formatEventString(header, event);
    }
}
