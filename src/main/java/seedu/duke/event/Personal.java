package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Personal extends Event {

    public Personal(String description, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setDate(date);
        setTime(time);
    }

    public Personal(String description, LocalDate date, LocalTime time) {
        this(description, date, time, 0);
    }

    public Personal(String description, LocalDate date) {
        this(description, date, null, 0);
    }

    public Personal(String description) {
        this(description, null, null);
    }

    @Override
    public Personal clone() throws CloneNotSupportedException {
        return (Personal) super.clone();
    }

    @Override
    public String toString() {
        if (time != null && date != null) {
            return "[P]" + super.toString() + " on " + date + ", " + time;
        } else if (time == null && date != null) {
            return "[P]" + super.toString() + " on " + date;
        } else {
            return "[P]" + super.toString();
        }
    }

    @Override
    public String toCalendarString() {
        return "P | " + super.toCalendarString();
    }
}
