package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DailyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public DailyEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        super(title, date, time, isToRemind);
    }

//    @Override
//    public ArrayList<Event> getRecurrances(LocalDate startDate, LocalDate endDate) {
//        ArrayList<Event> eventSet = new ArrayList<Event>();
//        LocalTime timing = getTime();
//        while (startDate.compareTo(endDate) <= 0) {
//            if (checkAfterEndRecurrance(startDate)) {
//                return eventSet;
//            }
//            LocalDateTime dateTime = LocalDateTime.of(startDate, timing);
//            Event e = new Event(getTitle(), dateTime, getToRemind(), false);
//            eventSet.add(e);
//            startDate = timeStep(startDate);
//        }
//        return eventSet;
//    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = getDate().plusDays(TIME_STEP);
        return new DailyEvent(getTitle(), date, getTime(), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusDays(TIME_STEP);
    }
}
