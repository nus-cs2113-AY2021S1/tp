package seedu.duke.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.temporal.TemporalAdjusters.next;

public class Event {
    protected String eventName;
    protected String eventTime;
    protected LocalDate date;
    protected String SYMBOL;
    protected boolean isDone;


    public Event(String name, String date, String time) {
        this.eventName = name;
        this.eventTime = time;
        setDateTime(date, time);
        this.SYMBOL = "[E]";
        this.isDone = false;
    }

    public void setEventDate(LocalDate date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public LocalDate getEventDate() {
        return date;
    }

    /**
     * Returns a tick or cross depending on whether a event is marked done.
     *
     * @return done or upcoming command
     */
    public String getStatusIcon() {
        return isDone ? "[Done]" : "[Up-coming]";
    }

    /**
     * Returns the string format of the event.
     *
     * @return String format of event.
     */
    public String printEvent() {
        return SYMBOL + this.getStatusIcon() + "\nEvent Name: " + this.eventName +  "\nDate: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\nTime: " + this.eventTime;
    }

    /**
     * Attempts to read the date time. If it fails, uses relative timing to try again.
     * @param dateStr The Date to be processed
     * @param timeStr The time to be processed
     */
    public void setDateTime(String dateStr, String timeStr) {
        eventTime = timeStr;
        try {
            date = LocalDate.parse(dateStr);

        } catch (DateTimeParseException e) {
            // Ignore
        }
        if (date != null) {
            return;
        }
        LocalDateTime start = null;
        if (dateStr.toLowerCase().contains("next")) {
            if (dateStr.toLowerCase().matches("next\\s*mon.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.MONDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*tue.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.TUESDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*wed.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.WEDNESDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*thu.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.THURSDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*fri.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.FRIDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*sat.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.SATURDAY));
            } else if (dateStr.toLowerCase().matches("next\\s*sun.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.SUNDAY));
            }
        }
        if (dateStr.toLowerCase().contains("end")) {
            if (dateStr.toLowerCase().matches("end\\s*mon.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.MONDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*tue.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.TUESDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*wed.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.WEDNESDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*thu.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.THURSDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*fri.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.FRIDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*sat.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.SATURDAY));
            } else if (dateStr.toLowerCase().matches("end\\s*sun.*")) {
                start = LocalDateTime.now().with(next(DayOfWeek.SUNDAY));
            }
            if (start != null) {
                start = start.withHour(23).withMinute(59).withSecond(59).withNano(0);
                eventTime = "23:59:59";
            }
        }
        if (start != null) {
            date = start.toLocalDate();
        }
    }
}

