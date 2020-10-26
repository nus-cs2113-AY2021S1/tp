package seedu.duke.event;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Timetable extends Event {
    protected String location;

    public Timetable(String description, String location, LocalDate date, LocalTime time) {
        super(description);
        setLocation(location);
        setDate(date);
        setTime(time);
    }

    public Timetable(String description, LocalDate date, LocalTime time) {
        this(description, null, date, time);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public Timetable clone() throws CloneNotSupportedException {
        return (Timetable) super.clone();
    }

    @Override
    public String toString() {
        if (location != null) {
            return "[T]" + super.toString() + ", Location: " + location + " on "  + date + ", " + time;
        } else {
            return "[T]" + super.toString() + " on " + date + ", " + time;
        }
    }

    @Override
    public String toCalendarString() {
        return "T | " + super.toCalendarString() + String.format("| %s", getLocation());
    }
}
