package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a tutorial event.
 */
public class Tutorial extends SchoolEvent {
    protected String eventType;

    private static final String TUTORIAL_FILE_SYMBOL = "TUT";
    private static final String SEPARATOR = "|";
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    /**
     * A Constructor of a tutorial object.
     *
     * @param moduleCode module code of the tutorial
     * @param date       date of the tutorial
     * @param time       time of the tutorial
     * @param venue      venue of the tutorial
     */
    public Tutorial(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        eventType = "TUT";
        this.isOver = getIsOver();
    }

    /**
     * Checks whether the tutorial is over.
     *
     * @return whether the tutorial is over
     */
    public boolean getIsOver() {
        LocalDateTime dateAndTime = LocalDateTime.of(date, time);
        ZonedDateTime due = ZonedDateTime.of(dateAndTime, ZoneId.of("+08:00"));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("+08:00"));

        if (due.isBefore(now)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shows whether the tutorial is over.
     *
     * @return whether the tutorial is over
     */
    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Describes the tutorial event.
     *
     * @Return a string to describe the tutorial event.
     */
    @Override
    public String toString() {
        return "[TUT]" + "[" + getIcon() + "] " + super.toString();
    }

    /**
     * Gets the description of the tutorial.
     *
     * @return the description of the tutorial.
     */
    @Override
    public String getDescription() {
        return "[TUT]" + "[" + getIcon() + "] " + super.getDescription();
    }

    /**
     * Returns the description of the recurring tutorial.
     */
    @Override
    public String getRecurringDescription() {
        return "[TUT]" + "[R] " + super.getRecurringDescription();
    }

    /**
     * Saves the tutorial event into files.
     *
     * @return string contains the information about the tutorial event.
     */
    @Override
    public String printIntoFile() {
        String writeToFile;
        writeToFile = TUTORIAL_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time + SEPARATOR + venue
                + SEPARATOR + getAdditionalInformationCount();
        if (getAdditionalInformationCount() != 0) {
            int i;
            for (i = 0; i < getAdditionalInformationCount(); i++) {
                writeToFile = writeToFile + SEPARATOR + getAdditionalInformationElement(i);
            }
        }
        return writeToFile;
    }

    /**
     * Returns the respective type.
     */
    @Override
    public String getType() {
        return eventType;
    }

    /**
     * Gets the date of the tutorial.
     *
     * @return date of the tutorial.
     */
    @Override
    public LocalDate getDate() {
        return date;
    }


    /**
     * Gets the time of the tutorial.
     *
     * @return time of the tutorial
     */
    @Override
    public LocalTime getTime() {
        return time;
    }
}
