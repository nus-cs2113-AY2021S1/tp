package seedu.duke.command;

import java.time.LocalDate;

/**
 * Lists all the Events in the Timetable.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable. Parameters: ";

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
        return null;
    }
}
