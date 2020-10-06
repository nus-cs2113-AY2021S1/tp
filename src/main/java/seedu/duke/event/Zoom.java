package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zoom extends Event {
    protected String zoomLink;

    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time, int repeatCount) {
        super(description);
        setZoomLink(zoomLink);
        setDate(date);
        setTime(time);
        setRepeatCount(repeatCount);
    }

    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time) {
        this(description, zoomLink, date, time, 0);
    }

    public Zoom(String description, String zoomLink) {
        this(description, zoomLink, null, null);
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    @Override
    public String toString() {
        return "[Z]" + super.toString();
    }
}
