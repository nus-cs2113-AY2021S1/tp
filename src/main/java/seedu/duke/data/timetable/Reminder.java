package seedu.duke.data.timetable;

import java.time.LocalDate;

public class Reminder {
    private Event event;
    private LocalDate dateToRemind;

    public Reminder(Event event, LocalDate dateToRemind) {
        this.event = event;
        this.dateToRemind = dateToRemind;
    }

    public String toString() {
        return event.toReminderString();
    }
}
