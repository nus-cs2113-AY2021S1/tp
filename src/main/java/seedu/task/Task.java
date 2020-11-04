package seedu.task;

import seedu.data.TaskMap;

import seedu.data.TimerCanceler;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;

import seedu.commands.Reminder;
import seedu.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

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
    private Reminder reminder;

    public Task(String description, String dateString, String startTime, String endTime, String priorityString,
                String reminderString, String reminderTimeString)
        throws InvalidPriorityException, InvalidDatetimeException, InvalidReminderException {
        this.description = description;
        date = dateStringToDate(dateString);
        this.startTime = timeStringToTime(startTime);
        this.endTime = timeStringToTime(endTime);
        priority = priorityStringToPriority(priorityString);
        taskID = generateHashValue();
        reminder = initiateReminder(reminderString, reminderTimeString);
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

    public Task(String taskID, String description, String dateString,
                String startTime, String endTime, String priorityString, String reminderString,
                String reminderTimeString)
        throws InvalidPriorityException, InvalidDatetimeException, InvalidReminderException {
        this.description = description;
        date = dateStringToDate(dateString);
        this.startTime = timeStringToTime(startTime);
        this.endTime = timeStringToTime(endTime);
        priority = priorityStringToPriority(priorityString);
        this.taskID = Integer.parseInt(taskID);
        this.reminder = initiateReminder(reminderString, reminderTimeString);
    }

    private Reminder initiateReminder(String reminderString, String remainderTimeString)
            throws InvalidReminderException, InvalidDatetimeException {
        LocalTime time = timeStringToTime(remainderTimeString);
        if (reminderString == null) {
            reminderString = "off";
        }
        switch(reminderString) {
            case "on":
                    return new Reminder(time);
            case "off":
                if(reminder != null) {
                    offReminder();
                }
                return null;
            default:
                throw new InvalidReminderException();
        }
    }

    public void startReminder() {
        if(reminder == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        if (reminder.getTime() != null) {
            calendar.set(getDate().getYear(), getDate().getMonthValue() - 1,
                    getDate().getDayOfMonth(), (reminder.getTime().getHour()), (reminder.getTime().getMinute()),0);
        } else {
            calendar.set(getDate().getYear(), getDate().getMonthValue() - 1,
                    getDate().getDayOfMonth(), getStartTime().getHour() - 1,
                    getStartTime().getMinute(),0);
        }
        Date date = calendar.getTime();
        reminder.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Reminder, you have an upcoming task: ");
                Ui ui = new Ui();
                ui.displaySingleTask(getDate(),getStartTime(),getEndTime(),getPriority(),getTaskID(),getDescription());
                reminder.getTimer().cancel();
                reminder.setIsReminder(false);
                reminder = null;
            }
        }, date);
    }

    public void offReminder() {
        reminder.getTimer().cancel();
        reminder.setIsReminder(false);
        reminder = null;
    }

    public Reminder getReminder() {
        return reminder;
    }
    public void setReminder(String reminderString, String reminderTime)
            throws InvalidReminderException, InvalidDatetimeException {
        reminder = initiateReminder(reminderString, reminderTime);
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

    private LocalDate dateStringToDate(String dateString) throws InvalidDatetimeException {
        if (dateString == null) {
            return LocalDate.now();
        }
        String[] dateParts = dateString.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }

    }

    private LocalTime timeStringToTime(String timeString) throws InvalidDatetimeException {
        if (timeString == null) {
            return null;
        }
        int time = Integer.parseInt(timeString);
        int hour = time / 100;
        int minute = time % 100;

        try {
            return LocalTime.of(hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }
    }

    private Priority priorityStringToPriority(String priorityString) throws InvalidPriorityException {
        if (priorityString == null) {
            return Priority.LOW;
        }
        Priority priority;
        switch (priorityString) {
        case "1":
            priority = Priority.LOW;
            break;
        case "2":
            priority = Priority.MEDIUM;
            break;
        case "3":
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

    public void setDate(String dateString) throws InvalidDatetimeException {
        date = dateStringToDate(dateString);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) throws InvalidDatetimeException {
        this.startTime = timeStringToTime(startTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) throws InvalidDatetimeException {
        this.endTime = timeStringToTime(endTime);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priorityString) throws InvalidPriorityException {
        priority = priorityStringToPriority(priorityString);
    }

    public String toString() {
        return taskID.toString() + " " + description + dateToString(date) + timeToString(startTime)
                + timeToString(endTime) + priorityToString(priority);
    }
}
