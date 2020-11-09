package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a lab event.
 */
public class Lab extends SchoolEvent {
    protected String eventType;

    private static final String LAB_FILE_SYMBOL = "LAB";
    private static final String SEPARATOR = "|";
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    /**
     * A Constructor of a lab object.
     *
     * @param moduleCode module code of the lab
     * @param date       date of the lab
     * @param time       time of the lab
     * @param venue      venue of the lab
     */
    public Lab(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        eventType = "LAB";
        this.isOver = getIsOver();
    }

    /**
     * Checks whether the lab is over.
     *
     * @return whether the lab is over
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
     * Shows whether the lab is over.
     *
     * @return whether the lab is over
     */
    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Describes the lab event.
     *
     * @return a string containing the information about the lab event
     */
    @Override
    public String toString() {
        return "[LAB]" + "[" + getIcon() + "] " + super.toString();
    }

    /**
     * Returns the description of the lab.
     */
    @Override
    public String getDescription() {
        return "[LAB]" + "[" + getIcon() + "] " + super.getDescription();
    }

    /**
     * Returns the description of the recurring lab.
     */
    @Override
    public String getRecurringDescription() {
        return "[LAB]" + "[R] " + super.getRecurringDescription();
    }

    /**
     * Saves the lab event into files.
     *
     * @return string contains the information about the lab event.
     */
    @Override
    public String printIntoFile() {
        String writeToFile;
        writeToFile = LAB_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + moduleCode
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
     * Returns the respective object type.
     */
    @Override
    public String getType() {
        return eventType;
    }

    /**
     * Get the date of the lab.
     *
     * @return date of the lab
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the time of the lab.
     *
     * @return time of the lab
     */
    @Override
    public LocalTime getTime() {
        return time;
    }
}
