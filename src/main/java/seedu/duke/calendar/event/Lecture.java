package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a lecture event.
 */
public class Lecture extends SchoolEvent {
    protected String eventType;

    private static final String LECTURE_FILE_SYMBOL = "LEC";
    private static final String SEPARATOR = "|";
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    /**
     * A Constructor of a lecture object.
     *
     * @param moduleCode module code of the lecture
     * @param date       date of the lecture
     * @param time       time of the lecture
     * @param venue      venue of the lecture
     */
    public Lecture(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        eventType = "LEC";
        this.isOver = getIsOver();
    }

    /**
     * Checks whether the lecture is over.
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
     * Shows whether the lab is over.
     *
     * @return whether the lab is over
     */
    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Describes the lecture event.
     *
     * @Return a string to describe the lecture event.
     */
    @Override
    public String toString() {
        return "[LEC]" + "[" + getIcon() + "] " + super.toString();
    }


    /**
     * Returns the description of the lecture.
     */
    @Override
    public String getDescription() {
        return "[LEC]" + "[" + getIcon() + "] " + super.getDescription();
    }

    /**
     * Returns the description of the recurring lecture.
     */
    @Override
    public String getRecurringDescription() {
        return "[LEC]" + "[R] " + super.getRecurringDescription();
    }

    /**
     * Saves the lecture event into files.
     *
     * @return string contains the information about the lecture event.
     */
    @Override
    public String printIntoFile() {
        String writeToFile;
        writeToFile = LECTURE_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time + SEPARATOR + venue
                + SEPARATOR + getAdditionalInformationCount();
        if (getAdditionalInformationCount() != 0) {
            int i;
            for (i = 0; i < getAdditionalInformationCount(); i++) {
                writeToFile = writeToFile + (SEPARATOR + getAdditionalInformationElement(i));
            }
        }
        return writeToFile;
    }

    /**
     * Gets the date of the lecture.
     *
     * @return date of the lecture
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the time of the lecture.
     */
    @Override
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the respective object type.
     */
    @Override
    public String getType() {
        return eventType;
    }

}
