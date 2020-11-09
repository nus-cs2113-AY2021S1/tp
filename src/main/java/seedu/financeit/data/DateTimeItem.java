package seedu.financeit.data;

import seedu.financeit.parser.DateTimeOutputManager;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A children class of abstract Item.
 * DateTimeItem classes will have LocalDate and LocalTime attributes,
 * as well as associated methods for setting or presenting the attributes,
 * which can be used if it is characteristic of the item represented.
 */
public abstract class DateTimeItem extends Item {
    protected LocalDate date;
    protected LocalTime time;
    protected DateTimeOutputManager dateTimeOutputManager = new DateTimeOutputManager();
    protected String defaultDateTimeFormat;

    public DateTimeItem() {
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dateTimeOutputManager = new DateTimeOutputManager();
        this.dateTimeOutputManager.setDate(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
        this.dateTimeOutputManager = new DateTimeOutputManager();
        this.dateTimeOutputManager.setTime(time);
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setDefaultDateTimeFormat(String format) {
        this.defaultDateTimeFormat = format;
    }

    public String getDateFormatted() {
        return this.dateTimeOutputManager.getSingleDateFormatted(this.defaultDateTimeFormat);
    }

    public String getTimeFormatted() {
        return this.dateTimeOutputManager.getSingleTimeFormatted(this.defaultDateTimeFormat);
    }
}
