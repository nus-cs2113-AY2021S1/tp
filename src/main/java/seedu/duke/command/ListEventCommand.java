package seedu.duke.command;

import seedu.duke.data.timetable.Event;
import seedu.duke.ui.InterfaceManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TIMING;

/**
 * Lists all the Events in the Timetable.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    //private static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable.";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": List all the events in the Timetable. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TIMING + " YEAR-MONTH(1-12)]";

    private int year;
    private int month;
    private boolean isListByDate;

    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Constructs a ListEventCommand to list all the Events in the Timetable.
     */
    public ListEventCommand() {
        this.year = 0;
        this.month = 0;
        this.isListByDate = false;
    }


    public ListEventCommand(int year, int month) {
        this.year = year;
        this.month = month;
        this.isListByDate = true;
    }

    public ListEventCommand(int year) {
        this.year = year;
        this.month = 0;
    }

    @Override
    public String execute() {
        String result = "";

        if (year == 0) {
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

        // Display the whole year if no month, else display only that month.
        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar;
        if (month != 0) {
            calendar = timetable.getMonthTimetable(year, month);
        } else {
            calendar = timetable.getYearTimetable(year);
        }

        boolean first = true;
        for (String month : calendar.keySet()) {
            String monthEventsString = "";
            monthEventsString = month + InterfaceManager.LS;
            HashMap<Integer, ArrayList<Event>> monthCalendar = calendar.get(month);
            int i = 1;
            for (Integer day : monthCalendar.keySet()) {
                ArrayList<Event> dailyEvents = monthCalendar.get(day);
                // Sort does not seem to be working
                Collections.sort(dailyEvents);
                for (Event event : dailyEvents) {
                    monthEventsString += InterfaceManager.LS + String.format("%d.", i++) + event.toString();
                }
            }
            if (!first) {
                result += InterfaceManager.LS + InterfaceManager.LS;
            }
            first = false;
            result += monthEventsString;
        }
        return result;
    }
}
