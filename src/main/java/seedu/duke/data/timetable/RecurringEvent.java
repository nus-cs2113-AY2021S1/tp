package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class RecurringEvent extends Event {
    private LocalDate endRecurrance;
    private String recurrenceType;

    public static final LocalDate DEFAULT_END_RECURRANCE = LocalDate.of(3000, 12, 31);
    public static final String DAILY_RECURRANCE = "daily";
    public static final String WEEKLY_RECURRANCE = "weekly";
    public static final String MONTHLY_RECURRANCE = "monthly";
    public static final String YEARLY_RECURRANCE = "yearly";

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrance, String recurrenceType) {
        super(title, dateTime, isToRemind, true);
        this.endRecurrance = endRecurrance;
        this.recurrenceType = recurrenceType;
    }

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, String recurrenceType) {
        this(title, dateTime, isToRemind, DEFAULT_END_RECURRANCE, recurrenceType);
    }

    public boolean checkAfterEndRecurrance(LocalDate date) {
        return (endRecurrance.compareTo(date) < 0);
    }

    public ArrayList<Event> getRecurrances(LocalDate startDate, LocalDate endDate) {
        ArrayList<Event> eventSet = new ArrayList<Event>();
        LocalTime timing = getTime();
        while (startDate.compareTo(endDate) <= 0) {
            if (checkAfterEndRecurrance(startDate)) {
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
