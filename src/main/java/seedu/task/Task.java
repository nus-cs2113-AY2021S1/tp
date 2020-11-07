package seedu.task;

import seedu.commons.Util;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;

import java.time.LocalDate;
import java.time.LocalTime;


import static java.lang.Math.pow;


public class Task {
    // MAX_NUM_TASKS = 10000
    private static final int HASH_VALUE_DIGITS = 4;
    private Integer taskID;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    public Reminder reminder;

    public Task(String description, String dateString, String startTime, String endTime, String priorityString,
                String reminderString, String reminderTimeString)
        throws InvalidPriorityException, InvalidDatetimeException, InvalidReminderException {
        this.description = description;
        date = Util.dateStringToDate(dateString);
        this.startTime = Util.timeStringToTime(startTime);
        this.endTime = Util.timeStringToTime(endTime);
        priority = Util.priorityStringToPriority(priorityString);
        taskID = generateHashValue();
        reminder = new Reminder();
        initiateReminder(reminderString, reminderTimeString);
    }

    public Task(Integer taskID, String description, LocalDate date,
                LocalTime startTime, LocalTime endTime, Priority priority, Reminder reminder) {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.taskID = taskID;
        this.reminder = reminder;
    }

    public Task(String description, LocalDate date,
                LocalTime startTime, LocalTime endTime, Priority priority)
        throws InvalidDatetimeException, InvalidReminderException {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.taskID = generateHashValue();
        reminder = new Reminder();
        initiateReminder(null, null);
    }

    public Task(String taskID, String description, String dateString,
                String startTime, String endTime, String priorityString, String reminderString,
                String reminderTimeString)
        throws InvalidPriorityException, InvalidDatetimeException, InvalidReminderException {
        this.description = description;
        date = Util.dateStringToDate(dateString);
        this.startTime = Util.timeStringToTime(startTime);
        this.endTime = Util.timeStringToTime(endTime);
        priority = Util.priorityStringToPriority(priorityString);
        this.taskID = Integer.parseInt(taskID);
        this.reminder = new Reminder();
        initiateReminder(reminderString, reminderTimeString);
    }

    private void initiateReminder(String reminderString, String remainderTimeString)
            throws InvalidReminderException, InvalidDatetimeException {
        LocalTime time = Util.timeStringToTime(remainderTimeString);
        if (reminderString == null) {
            reminderString = "off";
        }
        switch (reminderString) {
        case "on":
            reminder.setIsOn(true);
            if (time != null) {
                reminder.setTime(time);
            } else if (startTime != null) {
                reminder.setTime(LocalTime.of(getStartTime().getHour() - 1,
                        getStartTime().getMinute()));
            } else {
                throw new InvalidReminderException();
            }
            break;
        case "off":
            reminder.offReminder();
            break;
        default:
            throw new InvalidReminderException();
        }
    }

    public String getReminderString() {
        if (reminder.getIsOn()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public Reminder newReminder() {
        return new Reminder();
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(String reminderString, String reminderTime)
            throws InvalidReminderException, InvalidDatetimeException {
        initiateReminder(reminderString, reminderTime);
    }

    private int generateHashValue() {
        return hashCode() % (int) pow(10, HASH_VALUE_DIGITS);
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
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

    public void setDate(String dateString) throws InvalidDatetimeException {
        date = Util.dateStringToDate(dateString);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) throws InvalidDatetimeException {
        this.startTime = Util.timeStringToTime(startTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) throws InvalidDatetimeException {
        this.endTime = Util.timeStringToTime(endTime);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priorityString) throws InvalidPriorityException {
        priority = Util.priorityStringToPriority(priorityString);
    }

    public String toString() {
        return taskID.toString() + " " + description + Util.dateToString(date) + Util.timeToString(startTime)
                + Util.timeToString(endTime) + Util.priorityToString(priority);
    }
}
