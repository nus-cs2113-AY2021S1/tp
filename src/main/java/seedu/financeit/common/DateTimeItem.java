package seedu.financeit.common;

import seedu.financeit.parser.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class DateTimeItem extends Item {
    protected LocalDate date;
    protected LocalTime time;
    protected DateTimeManager dateTimeManager = new DateTimeManager();
    protected String defaultDateTimeFormat;

    public DateTimeItem() {
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dateTimeManager = new DateTimeManager();
        this.dateTimeManager.setDate(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
        this.dateTimeManager = new DateTimeManager();
        this.dateTimeManager.setTime(time);
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setDefaultDateTimeFormat(String format) {
        this.defaultDateTimeFormat = format;
    }

    public String getDateFormatted() {
        return this.dateTimeManager.getSingleDateFormatted(this.defaultDateTimeFormat);
    }

    public String getTimeFormatted() {
        return this.dateTimeManager.getSingleTimeFormatted(this.defaultDateTimeFormat);
    }
}
