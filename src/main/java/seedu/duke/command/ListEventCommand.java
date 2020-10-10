package seedu.duke.command;

import java.time.LocalDate;

import static seedu.duke.util.PrefixSyntax.PREFIX_DATETIME;

/**
 * Lists all the Events in the Timetable.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable. Parameters: "
            + "[" + PREFIX_DATETIME + " DATE_TIME]";

    private LocalDate date;
    private boolean isListByDate;

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

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
        return timetable.getEvents().toString();
    }
}
