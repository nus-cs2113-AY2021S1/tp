package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timetable extends Event {
    protected String location;

    public Timetable(String description, String location, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setLocation(location);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
    }

    public Timetable(String description, String location, LocalDate date, LocalTime time) {
        this(description, location, date, time, 0);
    }

    public Timetable(String description, LocalDate date, LocalTime time) {
        this(description, null, date, time);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
