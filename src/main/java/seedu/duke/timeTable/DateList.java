package seedu.duke.timeTable;

import java.util.ArrayList;
import java.util.List;

public class DateList {
    public List<EventList> dateList;

    public DateList() {
        dateList = new ArrayList<>();
    }

    public void addLesson(Event lesson){
        for(Duration duration: lesson.periods){
            boolean existList = false;
            for(EventList eventList: dateList){
                if(eventList.dateTag == duration.startDateTime.toLocalDate()){
                    eventList.addEvent(lesson);
                    existList = true;
                }
            }
            if(!existList) {
                EventList newList = new EventList(duration.startDateTime);
                newList.addEvent(lesson);
                dateList.add(newList);
            }
        }
    }

}
