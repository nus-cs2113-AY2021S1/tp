package seedu.duke.command;

import seedu.duke.data.timetable.Event;
import seedu.duke.ui.InterfaceManager;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Lists all the Events in the Timetable.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable.";

    //public static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable. Parameters: "
    //        + "[" + PREFIX_DATETIME + " DATE_TIME]";

    private LocalDate date;
    private boolean isListByDate;

    /**
     * Constructs a ListEventCommand to list all the Events in the Timetable.
     */
    public ListEventCommand() {
        this.date = null;
        this.isListByDate = false;
    }

    /**
     * Constructs a ListEventCommand to list all the Events in the Timetable.
     *
     * @param date of the Event.
     */
    public ListEventCommand(LocalDate date) {
        this.date = date;
        this.isListByDate = true;
    }

    @Override
    public String execute() {
        String result = "";
        ArrayList<Event> events = timetable.getEvents();
        boolean first = true;
        int i = 1;
        for (Event event : events) {
            if (!first) {
                result += InterfaceManager.LS + InterfaceManager.LS;
            }
            first = false;
            result += String.format("%d.", i++) + event.toString();
        }
        return result;
    }
}
