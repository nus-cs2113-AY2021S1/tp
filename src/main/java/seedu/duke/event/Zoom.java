package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zoom extends Event {
    protected String zoomLink;

    /**
     * Constructor for Zoom Event with all details given.
     *
     * @param description of Zoom Event.
     * @param zoomLink    of Zoom Event.
     * @param date        of Zoom Event.
     * @param time        of Zoom Event.
     */
    public Zoom(String description, String zoomLink, LocalDate date, LocalTime time) {
        super(description);
        setZoomLink(zoomLink);
        setDate(date);
        setTime(time);
    }

    /**
     * Constructor for Zoom Event without date and time.
     *
     * @param description of Zoom Event.
     * @param zoomLink    of Zoom Event.
     */
    public Zoom(String description, String zoomLink) {
        this(description, zoomLink, null, null);
    }

    /**
     * Sets the link of the Zoom Event.
     *
     * @param zoomLink of Zoom Event.
     */
    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    /**
     * Returns the link of the Zoom Event.
     *
     * @return link of Zoom Event.
     */
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
