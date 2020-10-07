package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timetable extends Event {
    protected String location;
    protected Boolean hasLocation;

    public Timetable(String description, String location, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setLocation(location);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
        hasLocation = true;
    }

    public Timetable(String description, String location, LocalDate date, LocalTime time) {
        this(description, location, date, time, 0);
        hasLocation = true;
    }

    public Timetable(String description, LocalDate date, LocalTime time) {
        this(description, null, date, time);
        hasLocation = false;
    }

    public void setLocation(String location) {
        this.location = location;
        hasLocation = true;
    }

    @Override
    public String toString() {
        if(hasLocation) {
            return "[T]" + super.toString() + ", Location: " + location + " on "  + date + ", " + time;
        } else {
            return "[T]" + super.toString() + " on " + date + ", " + time;
        }
    }
}
