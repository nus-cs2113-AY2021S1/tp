package timetable;

import java.util.ArrayList;
import java.util.List;

public class DateList {
    public List<EventList> dateList;
    public List<Event> events;

    public DateList() {
        dateList = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void addLesson(Event lesson) {
        for (Duration duration: lesson.periods) {
            boolean existList = false;
            for (EventList eventList: dateList) {
                if (eventList.dateTag.equals(duration.startDateTime.toLocalDate())) {
                    eventList.addEvent(lesson);
                    existList = true;
                }
            }
            if (!existList) {
                EventList newList = new EventList(duration.startDateTime);
                newList.addEvent(lesson);
                dateList.add(newList);
            }
        }
        events.add(lesson);
    }

}
