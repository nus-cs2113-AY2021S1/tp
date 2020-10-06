package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Personal extends Event {
    public Personal(String description, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
    }

    public Personal(String description, LocalDate date, LocalTime time) {
        this(description, date, time, 0);
    }

    public Personal(String description) {
        this(description, null, null);
    }
}
