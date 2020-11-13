package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timetable extends Event {
    protected String location;

    /**
     * Constructor for Timetable Event with all details given.
     *
     * @param description of Timetable Event.
     * @param location    of Timetable Event.
     * @param date        of Timetable Event.
     * @param time        of Timetable Event.
     */
    public Timetable(String description, String location, LocalDate date, LocalTime time) {
        super(description);
        setLocation(location);
        setDate(date);
        setTime(time);
    }

    /**
     * Constructor for Timetable Event without location.
     *
     * @param description of Timetable Event.
     * @param date        of Timetable Event.
     * @param time        of Timetable Event.
     */
    public Timetable(String description, LocalDate date, LocalTime time) {
        this(description, null, date, time);
    }

    /**
     * Sets the location of the Timetable Event.
     *
     * @param location of Timetable Event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the location of the Timetable Event.
     *
     * @return location of Timetable Event.
     */
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
            return "[T]" + super.toString() + ", Location: " + location + " on " + date + ", " + time;
        } else {
            return "[T]" + super.toString() + " on " + date + ", " + time;
        }
    }

    @Override
    public String toCalendarString() {
        return "T | " + super.toCalendarString() + String.format("| %s", getLocation());
    }
}
