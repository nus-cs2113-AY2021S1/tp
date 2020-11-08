package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * Command to print events in a calendar format.
 */
public class CalendarCommand extends Command {
    private Map<LocalDate, ArrayList<Event>> calendarMap = new TreeMap<>();
    private int eventsWithoutDateCount = 0;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for calendar command.
     *
     * @param command arguments for command, as of now is ignored.
     */
    public CalendarCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        ArrayList<EventList> eventLists = data.getAllEventLists();
        for (EventList list : eventLists) {
            ArrayList<Event> events = list.getEvents();
            addEventsToCalendar(events);
        }
        logger.fine("Calendar created successfully.");
        int calendarCount = calendarMap.size();
        ui.printCalendarStart(calendarCount, eventsWithoutDateCount);
        for (Map.Entry<LocalDate, ArrayList<Event>> entry : calendarMap.entrySet()) {
            ui.printCalendar(entry);
            if (calendarCount > 1) {
                ui.printContinueQuery();
                if (ui.receiveCommand().toLowerCase().equals("q")) {
                    break;
                }
            }
            calendarCount--;
        }
        ui.printCalendarEnd();
        logger.fine("Exited calendar mode successfully.");
    }

    /**
     * Adds events from event arraylist into the calendar treemap.
     *
     * @param events to add into the calendar.
     */
    private void addEventsToCalendar(ArrayList<Event> events) {
        for (Event e : events) {
            ArrayList<Event> eventRepeatList = e.getRepeatEventList();
            if (eventRepeatList != null) {
                addEventsToCalendar(eventRepeatList);
            }
            addEventToCalendar(e);
        }
    }

    private void addEventToCalendar(Event e) {
        LocalDate eventDate = e.getDate();
        LocalTime eventTime = e.getTime();
        if (eventDate != null && eventTime != null) {
            ArrayList<Event> eventsOnDate;
            if (calendarMap.containsKey(eventDate)) {
                eventsOnDate = calendarMap.get(eventDate);
                eventsOnDate.add(e);
            } else {
                eventsOnDate = new ArrayList<>();
                eventsOnDate.add(e);
                calendarMap.put(eventDate, eventsOnDate);
            }
        } else {
            eventsWithoutDateCount++;
        }
    }

    /**
     * Static parser for calendar command creation.
     *
     * @param input user input, as of now ignored.
     * @return CalendarCommand default to null.
     */
    public static Command parse(String input) {
        return new CalendarCommand(null);
    }
}
