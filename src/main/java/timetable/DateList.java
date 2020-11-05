package timetable;

import java.util.ArrayList;
import java.util.List;

public class DateList {
    public List<EventList> dateList;
    public List<Event> lessons;
    public List<Event> activities;

    public DateList() {
        dateList = new ArrayList<>();
        lessons = new ArrayList<>();
        activities = new ArrayList<>();
    }

    public void addEvent(Event event) throws ClashScheduleException {
        for (Duration duration: event.periods) {
            boolean existList = false;
            for (EventList eventList: dateList) {
                if (eventList.dateTag.equals(duration.startDateTime.toLocalDate())) {
                    if (clashDetection(duration, eventList)) {
                        throw new ClashScheduleException();
                    } else {
                        eventList.addEvent(event);
                        existList = true;
                    }
                }
            }
            if (!existList) {
                EventList newList = new EventList(duration.startDateTime);
                newList.addEvent(event);
                dateList.add(newList);
            }
        }
        if (event.eventType.equals(EventType.L)) {
            lessons.add(event);
        } else if (event.eventType.equals(EventType.A)) {
            activities.add(event);
        }
    }

    public boolean clashDetection(Duration duration, EventList eventList) {
        for (int i = 0; i < duration.timeSlot.size(); i++) {
            for (Event event: eventList.events) {
                for (Duration period: event.periods) {
                    if (duration.startDateTime.toLocalDate().equals(period.startDateTime.toLocalDate())
                        && period.containTimeSlot(duration.timeSlot.get(i))) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

}
