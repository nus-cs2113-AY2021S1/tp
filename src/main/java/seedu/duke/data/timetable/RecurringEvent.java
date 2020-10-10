package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class RecurringEvent extends Event {
    private LocalDate endRecurrance;
    public static LocalDate DEFAULT_END_RECURRANCE = LocalDate.of(3000, 12, 31);
    public RecurringEvent(String title, LocalDate date, LocalTime time, boolean isToRemind, LocalDate endRecurrance) {
        super(title, date, time, isToRemind, true);
        this.endRecurrance = endRecurrance;
    }

    public RecurringEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        this(title, date, time, isToRemind, DEFAULT_END_RECURRANCE);
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

    public abstract RecurringEvent stepOneTimePeriod();
    public abstract LocalDate timeStep(LocalDate date);

}
