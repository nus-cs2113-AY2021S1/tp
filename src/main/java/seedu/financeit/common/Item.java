package seedu.financeit.common;

import seedu.financeit.parser.DateTimeManager;

import java.time.LocalDateTime;

public abstract class Item extends ParamHandler {
    protected DateTimeManager dateTimeManager;
    protected LocalDateTime dateTime = null;
    protected int index = -1;

    public void setDefaultDateTimeFormat(String format) {
        this.defaultDateTimeFormat = format;
    }

    public Item() {
    }

    public Item(LocalDateTime dateTime) {
        this.setDateTime(dateTime);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public String getDateTimeFormatted() {
        return this.dateTimeManager.getDateFormatted(this.defaultDateTimeFormat);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public abstract String getName();
}
