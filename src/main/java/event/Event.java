package event;

import location.Location;
import location.OnlineLocation;

import java.time.LocalDateTime;
import java.util.Comparator;

import java.time.LocalDate;

/**
 * Represents the event objects.
 * Contains a String description , which is the description of the event,
 * and a boolean isDone, which is the status of the Event.
 * Provides methods to mark an event as done(either during user input or reading from file),
 * get the value of isDone,
 * convert the event object to a string to be printed out or write to a file.
 */
public abstract class Event {
    protected String description;
    protected boolean isDone;
    protected Location location = null;
    protected OnlineLocation link = null;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(OnlineLocation link) {
        this.link = link;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Event(String description, Location location) {
        this.description = description;
        this.isDone = false;
        this.location = location;
    }

    public Event(String description, OnlineLocation location) {
        this.description = description;
        this.isDone = false;
        this.link = location;
    }

    /**
     * Mark a Event's status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String fileString();

    /**
     * Provides the status of the Event as a icon.
     *
     * @return returns a tick if the Event is done, and returns a cross if the Event is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "Done" : "Not Done");
        //return tick or X symbols
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the common part for Event, Assignment, Class.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    /**
     * Provides the date of the Event.
     *
     * @return the LocalDate get from LocalDateTime.
     */
    public LocalDate getDate() {
        return null;
    }

    public LocalDateTime getStartDateTime() {
        return null;
    }

    public LocalDateTime getEndDateTime() {
        return null;
    }

    /**
     * Provides the String variable of the description of this Event.
     *
     * @return the description of this Event.
     */
    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public OnlineLocation getLink() {
        return link;
    }

    public static Comparator<Event> descriptionComparator = (o1, o2) ->
            o1.getDescription().compareToIgnoreCase(o2.getDescription());


    public static Comparator<Event> locationComparator = (e1, e2) -> {

        if (e2.getLocation() == null && e1.getLocation() == null) {
            if (e1.getLink() == null) {
                return 1;
            }
            if (e2.getLink() == null) {
                return -1;
            }
            return e1.getLink().getLink().compareToIgnoreCase(e2.getLink().getLink());
        }

        if (e1.getLocation() == null) {
            return 1;
        }
        if (e2.getLocation() == null) {
            return -1;
        }
        return e1.getLocation().getName().compareToIgnoreCase(e2.getLocation().getName());
    };

}



