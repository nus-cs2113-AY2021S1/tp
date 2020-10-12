package seedu.duke.data.timetable;

import java.time.LocalDate;

public class Reminder implements Comparable<Reminder> {
    private Event event;
    private LocalDate dateToRemind;

    /**
     * Constructor for a Reminder. Includes what event to remind about as well as what date to remind on.
     *
     * @param event Event to remind
     * @param dateToRemind When to remind
     */
    public Reminder(Event event, LocalDate dateToRemind) {
        this.event = event;
        this.dateToRemind = dateToRemind;
    }

    public LocalDate getDateToRemind() {
        return dateToRemind;
    }

    /**
     * Returns whether this reminder should be raised to the user.
     * If the date provided is equal the dateToRemind stored in the reminder, it returns true.
     *
     * @param date Date to check
     * @return Whether the reminder should be raised.
     */
    public boolean toRemind(LocalDate date) {
        return dateToRemind.equals(date);
    }

    @Override
    public String toString() {
        return event.toReminderString();
    }

    @Override
    public int compareTo(Reminder o) {
        return dateToRemind.compareTo(o.dateToRemind);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Reminder) {
            Reminder r = ((Reminder) o);
            if (r.dateToRemind.equals(dateToRemind) && r.event.getTitle().equals(event.getTitle())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
