package seedu.task;

import seedu.exceptions.InvalidPriorityException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Task {
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Priority priority;

    public Task(String description, String dateString,
                String timeString, String priorityString) throws InvalidPriorityException {
        this.description = description;
        date = dateStringToDate(dateString);
        time = timeStringToTime(timeString);
        priority = priorityStringToPriority(priorityString);
    }

    private LocalDate dateStringToDate(String dateString) {
        if (dateString == null) {
            return LocalDate.now();
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

    private Priority priorityStringToPriority(String priorityString) throws InvalidPriorityException {
        if (priorityString == null) {
            return Priority.LOW;
        }
        Priority priority;
        switch (priorityString) {
        case "0":
            priority = Priority.LOW;
            break;
        case "1":
            priority = Priority.MEDIUM;
            break;
        case "2":
            priority = Priority.HIGH;
            break;
        default:
            throw new InvalidPriorityException();
        }
        return priority;
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

    private String priorityToString(Priority priority) {
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


    public void setDate(String dateString) {
        date = dateStringToDate(dateString);
    }

    public LocalTime getTime() {
        return time;
    }


    public void setTime(String timeString) {
        time = timeStringToTime(timeString);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setPriority(String priorityString) throws InvalidPriorityException {
        priority = priorityStringToPriority(priorityString);
    }

    public String toString() {
        return description + dateToString(date) + timeToString(time) + priorityToString(priority);
    }
}
