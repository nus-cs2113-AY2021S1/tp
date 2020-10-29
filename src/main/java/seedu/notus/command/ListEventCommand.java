package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.CommandMessage.LIST_EVENT_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.LIST_EVENT_SUCCESSFUL_TIME_PERIOD_MESSAGE;
import static seedu.notus.util.CommandMessage.LIST_EVENT_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.LIST_EVENT_UNSUCCESSFUL_TIME_PERIOD_MESSAGE;
import static seedu.notus.util.CommandMessage.LIST_E_COMMAND_USAGE;

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



    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return LIST_E_COMMAND_USAGE;
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
                return Formatter.formatString(LIST_EVENT_UNSUCCESSFUL_MESSAGE);
            }
            return Formatter.formatTimetable(LIST_EVENT_SUCCESSFUL_MESSAGE, events);
        }

        // Display the whole year if no month, else display only that month.
        HashMap<Month, HashMap<Integer, ArrayList<Event>>> calendar;
        if (month != 0) {
            calendar = timetable.getMonthTimetable(year, month);
        } else {
            calendar = timetable.getYearTimetable(year);
        }
        if (calendar.size() == 0) {
            return Formatter.formatString(LIST_EVENT_UNSUCCESSFUL_TIME_PERIOD_MESSAGE);
        }

        return Formatter.formatTimetable(LIST_EVENT_SUCCESSFUL_TIME_PERIOD_MESSAGE, year, month, calendar);
    }
}
