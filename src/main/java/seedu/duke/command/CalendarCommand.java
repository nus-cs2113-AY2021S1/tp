package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CalendarCommand extends Command {
    private Map<LocalDate, ArrayList<Event>> calendarMap = new TreeMap<>();

    public CalendarCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        ArrayList<EventList> eventLists = data.getAllEventLists();
        for(EventList list : eventLists) {
            ArrayList<Event> events = list.getEvents();
            addEventsToCalendar(events);
        }
        for(Map.Entry<LocalDate, ArrayList<Event>> entry : calendarMap.entrySet()) {
            ui.printCalendar(entry);
        }
    }

    private void addEventsToCalendar(ArrayList<Event> events) {
        for (Event e : events) {
            addEventToCalendar(e);
        }
    }

    private void addEventToCalendar(Event e) {
        LocalDate eventDate = e.getDate();
        ArrayList<Event> eventsOnDate;
        if(calendarMap.containsKey(eventDate)) {
            eventsOnDate = calendarMap.get(eventDate);
            eventsOnDate.add(e);
        } else {
            eventsOnDate = new ArrayList<>();
            eventsOnDate.add(e);
            calendarMap.put(eventDate, eventsOnDate);
        }
    }

    public static Command parse(String input) {
        return new CalendarCommand(null);
    }
}
