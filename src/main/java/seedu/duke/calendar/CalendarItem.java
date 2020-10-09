package seedu.duke.calendar;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class CalendarItem {
    public abstract String printIntoFile();

    public abstract LocalTime getTime();

    public abstract LocalDate getDate();
}
