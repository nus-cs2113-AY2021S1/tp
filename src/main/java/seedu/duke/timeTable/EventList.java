package seedu.duke.timeTable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventList {
    public LocalDate dateTag;
    public List<Event> events;

    public EventList(LocalDateTime dateTime){
        dateTag = dateTime.toLocalDate();
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }
}
