package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class RecurringEvent extends Event {
    private LocalDate endRecurrence;
    private String recurrenceType;

    public static final LocalDate DEFAULT_END_RECURRENCE = LocalDate.of(3000, 12, 31);
    public static final String DAILY_RECURRENCE = "daily";
    public static final String WEEKLY_RECURRENCE = "weekly";
    public static final String MONTHLY_RECURRENCE = "monthly";
    public static final String YEARLY_RECURRENCE = "yearly";

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                          String recurrenceType) {
        super(title, dateTime, isToRemind, true);
        this.endRecurrence = endRecurrence;
        this.recurrenceType = recurrenceType;
    }

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, String recurrenceType) {
        this(title, dateTime, isToRemind, DEFAULT_END_RECURRENCE, recurrenceType);
    }

    public boolean checkAfterEndRecurrence(LocalDate date) {
        return (endRecurrence.compareTo(date) < 0);
    }

    public ArrayList<Event> getRecurrences(LocalDate startDate, LocalDate endDate) {
        ArrayList<Event> eventSet = new ArrayList<Event>();
        LocalTime timing = getTime();
        while (startDate.compareTo(endDate) <= 0) {
            if (checkAfterEndRecurrence(startDate)) {
                return eventSet;
            }
            LocalDateTime dateTime = LocalDateTime.of(startDate, timing);
            Event e = new Event(getTitle(), dateTime, getToRemind(), false);
            eventSet.add(e);
            startDate = timeStep(startDate);
        }
        return eventSet;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (%s)", recurrenceType);
    }

    public abstract RecurringEvent stepOneTimePeriod();

    public abstract LocalDate timeStep(LocalDate date);

}
