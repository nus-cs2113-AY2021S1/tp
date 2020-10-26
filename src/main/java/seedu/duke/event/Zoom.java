package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zoom extends Event {
    protected String zoomLink;

    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time) {
        super(description);
        setZoomLink(zoomLink);
        setDate(date);
        setTime(time);
    }

    public Zoom(String description, String zoomLink) {
        this(description, zoomLink, null, null);
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public String getZoomLink() {
        return this.zoomLink;
    }

    @Override
    public Zoom clone() throws CloneNotSupportedException {
        return (Zoom) super.clone();
    }

    @Override
    public String toString() {
        if (time != null && date != null) {
            return "[Z]" + super.toString() + ", Link: " + zoomLink + " on " + date + ", " + time;
        } else {
            return "[Z]" + super.toString() + ", Link: " + zoomLink;
        }
    }

    @Override
    public String toCalendarString() {
        return "Z | " + super.toCalendarString() + String.format("| %s", getZoomLink());
    }
}
