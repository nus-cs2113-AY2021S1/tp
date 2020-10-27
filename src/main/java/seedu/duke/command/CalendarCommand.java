package seedu.duke.command;

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

public class CalendarCommand extends Command {
    private Map<LocalDate, ArrayList<Event>> calendarMap = new TreeMap<>();
    private int eventsWithoutDateCount = 0;

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
        ui.printCalendarStart(calendarMap.size(), eventsWithoutDateCount);
        for (Map.Entry<LocalDate, ArrayList<Event>> entry : calendarMap.entrySet()) {
            ui.printCalendar(entry);
            if (ui.receiveCommand().toLowerCase().equals("q")) {
                break;
            }
        }
        ui.printCalendarEnd();
    }

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

    public static Command parse(String input) {
        return new CalendarCommand(null);
    }
}
