package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zoom extends Event {
    protected String zoomLink;
    protected Boolean hasDate;
    protected Boolean hasTime;

    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setZoomLink(zoomLink);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
        hasDate = true;
        hasTime = true;
    }

    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time) {
        this(description, zoomLink, date, time, 0);
        hasDate = true;
        hasTime = true;
    }

    public Zoom(String description, String zoomLink) {
        this(description, zoomLink, null, null);
        hasDate = false;
        hasTime = false;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    @Override
    public String toString() {
        if (hasTime && hasDate) {
            return "[Z]" + super.toString() + ", Link: " + zoomLink + " on "  + date + ", " + time;
        } else {
            return "[Z]" + super.toString() + ", Link: " + zoomLink;
        }
    }
}
