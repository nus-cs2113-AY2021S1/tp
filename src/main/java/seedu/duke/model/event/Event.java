package seedu.duke.model.event;

import java.util.Calendar;

//@@author elizabethcwt
public class Event implements Comparable<Event> {

    protected String description;
    protected boolean isDone;
    protected Calendar start;
    protected Calendar end;

    public Event(String description, Calendar start, Calendar end) {
        this.description = description;
        this.start = start;
        this.end = end;
        isDone = false;
    }

    //@@author durianpancakes
    public Event(String description, Calendar start, Calendar end, boolean isDone) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    //@@author
    public String getIcon() {
        return null;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    //@@author durianpancakes
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) obj;
        return this.description.equals(otherEvent.description)
                && this.isDone == otherEvent.isDone();
    }

    @Override
    public int compareTo(Event otherEvent) {
        if (this.getStart().before(otherEvent.start)) {
            return -1;
        } else {
            return 1;
        }
    }
}
