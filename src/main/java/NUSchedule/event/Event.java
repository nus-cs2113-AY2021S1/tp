package NUSchedule.event;


import NUSchedule.location.Location;

import java.time.LocalDate;

/**
 * Represents the event objects.
 * Contains a String <\code>description</\code> , which is the description of the event,
 * and a boolean <\code>isDone</\code>, which is the status of the Event.
 * Provides methods to mark an event as done(either during user input or reading from file),
 * get the value of <\code>isDone</\code>,
 * convert the <\code>event</\code> object to a string to be printed out or write to a file.
 */
public abstract class Event {
    protected String description;
    protected boolean isDone;
    protected Location location;//to be implemented
    public Event(String description) {
        this.description = description;
        this.isDone = false;
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
        return (isDone ? "\u2713" : "\u2718");
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

    /**
     * Provides the String variable of the description of this Event.
     *
     * @return the description of this Event.
     */
    public String getDescription() {
        return description;
    }
}



