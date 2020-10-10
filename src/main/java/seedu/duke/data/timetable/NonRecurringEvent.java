package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalTime;

public class NonRecurringEvent extends Event {
    public NonRecurringEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        super(title, date, time, isToRemind, false);
    }

    public Event setRecurring() {
        return new Event(getTitle(), getDate(), getTime(), getToRemind(), true);
    }
}
