package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;

//@@author brandonywl
/**
 * Lists all the Events in the Timetable. Can limit search to specific time periods.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-e";
    public static final int SMALLEST_YEAR = 1000;
    public static final int LARGEST_YEAR = 3000;
    public static final int SMALLEST_MONTH = 1;
    public static final int LARGEST_MONTH = 12;

    public static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TIMING + " YYYY or YYYY-MM]";

    private static final String COMMAND_SUCCESSFUL_GENERIC_MESSAGE = "These are the events stored: ";
    private static final String COMMAND_UNSUCCESSFUL_GENERIC_MESSAGE = "There are no events stored in the timetable!";
    private static final String COMMAND_SUCCESSFUL_TIME_PERIOD_MESSAGE = "These are the events "
            + "in the specified time period: ";
    private static final String COMMAND_UNSUCCESSFUL_TIME_PERIOD_MESSAGE = "Failed to find any events "
            + "in the specified time period.";

    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    private int year;
    private int month;

    /**
     * Constructs a ListEventCommand to list all the Events in the Timetable.
     */
    public ListEventCommand() {
        this.year = 0;
        this.month = 0;
    }


    /**
     * Constructs a ListEventCommand with a year and month to look in.
     *
     * @param year Year to look for events.
     * @param month Month to look for events.
     */
    public ListEventCommand(int year, int month) {
        this.year = year;
        this.month = month;
    }

    /**
     * Constructs a ListEventCommand with a year to look in.
     *
     * @param year Year to look for events.
     */
    public ListEventCommand(int year) {
        this.year = year;
        this.month = 0;
    }

    @Override
    public String execute() {
        // Just list all events, recurring or not, without repeat.
        if (year == 0) {
            ArrayList<Event> events = timetable.getEvents();
            if (events.size() == 0) {
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_GENERIC_MESSAGE);
            }
            return Formatter.formatTimetable(COMMAND_SUCCESSFUL_GENERIC_MESSAGE, events);
        }

        // Display the whole year if no month, else display only that month.
        HashMap<Month, HashMap<Integer, ArrayList<Event>>> calendar;
        if (month != 0) {
            calendar = timetable.getMonthTimetable(year, month);
        } else {
            calendar = timetable.getYearTimetable(year);
        }
        if (calendar.size() == 0) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_TIME_PERIOD_MESSAGE);
        }

        return Formatter.formatTimetable(COMMAND_SUCCESSFUL_TIME_PERIOD_MESSAGE, year, month, calendar);
    }
}
