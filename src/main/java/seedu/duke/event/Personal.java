package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Personal extends Event {
    protected Boolean hasDate;
    protected Boolean hasTime;

    public Personal(String description, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
        hasDate = true;
        hasTime = true;
    }

    public Personal(String description, LocalDate date, LocalTime time) {
        this(description, date, time, 0);
        hasDate = true;
        hasTime = true;
    }

    public Personal(String description, LocalDate date) {
        this(description, date, null);
        hasDate = true;
        hasTime = false;
    }

    public Personal(String description) {
        this(description, null, null);
        hasDate = false;
        hasTime = false;
    }

    @Override
    public String toString() {
        if(hasTime && hasDate) {
            return "[P]" + super.toString() + " on " + date + ", " + time;
        } else if (!hasTime && hasDate) {
            return "[P]" + super.toString() + " on " + date;
        } else {
            return "[P]" + super.toString();
        }
    }
}
