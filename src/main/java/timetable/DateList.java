package timetable;

import exceptions.ClashScheduleException;

import java.util.ArrayList;
import java.util.List;

public class DateList {
    public List<EventList> dateList;
    public List<Lesson> lessons;
    public List<Activity> activities;

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

    public void deleteActivity(int index, TimeTableStorage storage) {
        activities.remove(index - 1);
        dateList.clear();
        storage.wipeFile();
        for (Lesson lesson: lessons) {
            try {
                addEvent(lesson);
            } catch (ClashScheduleException e) {
                System.out.println("There is a clash in schedule");
            }
            storage.writeFile(lesson);
        }
        for (Activity activity: activities) {
            try {
                addEvent(activity);
            } catch (ClashScheduleException e) {
                System.out.println("There is a clash in schedule");
            }
            storage.writeFile(activity);
        }
    }

    public void deleteLesson(int index, TimeTableStorage storage) {
        lessons.remove(index - 1);
        dateList.clear();
        storage.wipeFile();
        for (Lesson lesson: lessons) {
            try {
                addEvent(lesson);
            } catch (ClashScheduleException e) {
                System.out.println("There is a clash in schedule");
            }
            storage.writeFile(lesson);
        }
        for (Activity activity: activities) {
            try {
                addEvent(activity);
            } catch (ClashScheduleException e) {
                System.out.println("There is a clash in schedule");
            }
            storage.writeFile(activity);
        }
    }
}
