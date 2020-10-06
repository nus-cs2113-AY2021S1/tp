package seedu.planus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    private LocalDate date;
    private LocalTime time;
    private Integer priority;

    public Task(String description, String dateString, String timeString, String priorityString) {
        this.description = description;
        date = dateStringToDate(dateString);
        time = timeStringToTime(timeString);
        priority = priorityStringToPriority(priorityString);

    }

    private LocalDate dateStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        String[] dateParts = dateString.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year, month, day);
    }

    private LocalTime timeStringToTime(String timeString) {
        if (timeString == null) {
            return null;
        }
        int time = Integer.parseInt(timeString);
        int hour = time / 100;
        int minute = time % 100;
        return LocalTime.of(hour, minute);
    }

    private Integer priorityStringToPriority(String priorityString) {
        if (priorityString == null) {
            return 0;
        }
        return Integer.parseInt(priorityString);
    }

    private String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        } else {
            return " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    private String timeToString(LocalTime time) {
        if (time == null) {
            return "";
        } else {
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    private String priorityToString(Integer priority) {
        return " " + priority.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String toString() {
        return description + dateToString(date) + timeToString(time) + priorityToString(priority);
    }
}
