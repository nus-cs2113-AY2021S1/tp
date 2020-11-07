package seedu.task;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.commands.DisplayReminder;
import seedu.data.Timers;
import seedu.ui.Ui;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents a reminder for a specific task, stores the time and timer needed to start a reminder.
 * An instance of this object is automatically created when a Task object is created.
 *
 */
public class Reminder {
    private LocalTime time;
    private boolean isOn;
    private transient Timer timer;

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void startTimer() {
        timer = new Timer();
    }

    public void setIsOn(boolean reminderStatus) {
        isOn = reminderStatus;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public LocalTime getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * Starts a timer and sets a schedule to print a specific task at a specified time.
     * @param task The task that is associated with the reminder.
     */
    public void startReminder(Task task) {
        if (!getIsOn()) {
            return;
        }
        startTimer();
        Timers.add(getTimer());
        Calendar calendar = Calendar.getInstance();
        calendar.set(task.getDate().getYear(), task.getDate().getMonthValue() - 1, task.getDate().getDayOfMonth(),
                (getTime().getHour()), (getTime().getMinute()),0);
        Date date = calendar.getTime();
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                Command display = new DisplayReminder(task);
                CommandResult result = display.execute();
                Ui ui = new Ui();
                ui.interpretCommandResult(result);
                offReminder();
            }
        }, date);
    }

    public void offReminder() {
        if (getIsOn()) {
            getTimer().cancel();
        }
        setIsOn(false);
    }
}
