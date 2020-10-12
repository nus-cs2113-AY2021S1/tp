package seedu.duke.timetable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Duration {
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public List<Integer> timeSlot;

    public Duration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        timeSlot = new ArrayList<>();
        int start = getTime(startDateTime);
        int end = getTime(endDateTime);
        for (int time = start; time < end; time += 100) {
            timeSlot.add(time);
        }
    }

    public Duration(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime.plusHours(1);
        timeSlot = new ArrayList<>();
        int start = getTime(startDateTime);
        int end = getTime(endDateTime);
        for (int time = start; time < end; time += 100) {
            timeSlot.add(time);
        }
    }

    private int getTime(LocalDateTime dateTime) {
        return dateTime.getHour() * 100 + dateTime.getMinute();
    }

    public boolean containTimeSlot(int timeSlot) {
        if (this.timeSlot.contains(timeSlot)) {
            return true;
        } else {
            return false;
        }
    }
}
