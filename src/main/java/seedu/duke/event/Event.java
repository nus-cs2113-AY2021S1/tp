package seedu.duke.event;

import seedu.duke.hr.Member;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.next;

public class Event {
    protected String eventName;
    protected String eventTime;
    protected LocalDate date;
    protected String symbol;
    protected boolean isDone;
    public ArrayList<Member> eventParticipants = new ArrayList<>();


    public Event(String name, String date, String time) {
        this.eventName = name;
        this.eventTime = time;
        this.symbol = "[E]";
        setDateTime(date, time);
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
        return symbol + this.getStatusIcon() + "\nEvent Name: " + this.eventName + "\nDate: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\nTime: " + this.eventTime;
    }


    public long numberOfDaysLeft() {
        return ChronoUnit.DAYS.between(LocalDate.now(), this.getEventDate());
    }

    /**
    * Attempts to read the date time. If it fails, uses relative timing to try again.
    *
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

    public void setEventParticipants(Member m) {
        if (m != null) {
            this.eventParticipants.add(m);
        }
    }

    public ArrayList<Member> getEventParticipants() {
        return eventParticipants;
    }

    public String getStringParticipants() {
        String output = "[";
        if (eventParticipants.get(0) != null) {
            for (int i = 0; i < eventParticipants.size(); i++) {
                output = output.concat(eventParticipants.get(i).getMemberName());
                if (i < (eventParticipants.size() - 1)) {
                    output = output.concat(" & ");
                }
            }
        }
        output = output.concat("]");
        return output;
    }

    /**
    * Used to identify if the string contains the keyword specified in its description.
    *
    * @param keyword The keyword to be matched with the description.
    * @return true if list contains the keyword
    */
    public boolean containsNameKeyword(String keyword) {
        boolean containsKeyword = eventName.toLowerCase().contains(keyword.toLowerCase());
        return containsKeyword;
    }

    public boolean containsDateKeyword(LocalDate localDate) {
        boolean containsKeyword = date.equals(localDate);
        return containsKeyword;
    }

    public String printEventParticipant() {
        String output = "The following member" + ((eventParticipants.size() == 1) ? "" : "s") + " had participated in " + eventName + " :\n";
        for (int i = 0; i < eventParticipants.size(); i++) {
            output = output.concat(eventParticipants.get(i).getMemberName() + "\n");
        }
        return output;
    }
}

