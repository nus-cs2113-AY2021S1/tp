package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Event {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;
    protected int repeatCount;
    protected boolean isDone;
    protected int repeatUnit; //weekly or monthly
    protected ArrayList<DateStatusPair> dateTimeList;
    protected boolean isRepeat;

    protected static final int WEEKLY = 1;
    protected static final int MONTHLY = 2;


    /**
     * Creates a new event with the given description and default its done status to false.
     *
     * @param description of event.
     */
    public Event(String description) {
        setDescription(description);
        isDone = false;
        isRepeat = false;
        dateTimeList = new ArrayList<>();
    }

    /**
     * Sets event's description.
     *
     * @param description of event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public void setRepeatUnit(int repeatUnit) {
        this.repeatUnit = repeatUnit;
    }

    public void setIsRepeat(boolean toRepeat) {
        this.isRepeat = toRepeat;
    }

    /**
     * Sets the event's done status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of event's done status.
     *
     * @return string representation of event's done status.
     */
    public String getStatus() {
        return (isDone) ? "✓" : "✕";
    }

    /**
     * Returns a string representation of event's done status for a particular index for repeated events.
     *
     * @param index integer of the index number of the repeated event date status pair
     * @return String representation of the event done status.
     */
    public String getStatus(int index) {

        boolean isEventDone;
        try {

            DateStatusPair currentDateStatusPair = this.dateTimeList.get(index);
            isEventDone = currentDateStatusPair.getDoneStatus();
            return (isEventDone) ? "✓" : "✕";

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Index out of bounds");
            return null;
        }
    }


    /**
     * Returns a String representation of an event's repeat status.
     *
     * @return string representation of event's repeat status, with starting date, repetition amount and time
     */
    public String getRepeatStatusString() {
        String dateString;
        String repeatNumber;
        String repeatTimeInterval;

        dateString = this.date.toString();
        repeatNumber = Integer.toString(repeatCount);
        switch (repeatUnit) {
        case WEEKLY:
            repeatTimeInterval = "Weekly";
            break;
        case MONTHLY:
            repeatTimeInterval = "Monthly";
            break;
        default:
            repeatTimeInterval = "NULL";

        }

        return "Repeat Status: " + repeatTimeInterval + " for " + repeatNumber + " time(s) starting from " + dateString;

    }

    /**
     * Gets date of the event.
     *
     * @return LocalDate object containing the date of the event
     */
    public LocalDate getDate() {

        return this.date;
    }

    /**
     * Gets date of the event stored at an index for repeated events.
     *
     * @param index Integer of the index number for the DateStatusPair object
     * @return LocalDate object containing the date at that iteration
     */
    public LocalDate getDate(int index) {

        try {
            DateStatusPair currentDateStatusPair = this.dateTimeList.get(index);
            return currentDateStatusPair.getDate();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Index out of bounds");
            return null;
        }

    }

    /**
     * Gets time of the event.
     *
     * @return LocalTime object containing the time of the event
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Gets time of the event stored at an index for repeated events.
     *
     * @param index Integer of the index number for the DateStatusPair object
     * @return LocalTime object containing the time at that iteration
     */
    public LocalTime getTime(int index) {

        try {
            DateStatusPair currentDateStatusPair = this.dateTimeList.get(index);
            return currentDateStatusPair.getTime();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Index out of bounds");
            return null;
        }
    }

    /**
     * Gets the status of whether the event has been repeated or not.
     *
     * @return boolean of repeat status
     */
    public boolean getIsRepeat() {
        return this.isRepeat;
    }

    /**
     * Gets the number of additional repetitions this event has.
     *
     * @return Integer containing number of repetitions
     */
    public int getRepeatCount() {
        return repeatCount;
    }

    /**
     * Function adds a DateStatusPair into the current Date Time list to indicate repeated events.
     *
     * @param date LocalDate representing date of the repeated event
     * @param time LocalTime representing time of the repeated event
     * @param state Boolean indicating if the event has been completed or not
     */
    public void addRepeatDateStatusPair(LocalDate date, LocalTime time, boolean state) {
        DateStatusPair toAdd = new DateStatusPair(date, time);
        toAdd.setDone(state);
        this.dateTimeList.add(toAdd);
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();

    }
}
