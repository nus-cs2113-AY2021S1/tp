package seedu.commands;

import java.time.LocalTime;
import java.util.Timer;

import seedu.data.TimerCanceler;
import seedu.task.Task;
import seedu.exceptions.InvalidTaskNumberException;


public class Reminder {
    private final LocalTime time;
    private boolean isReminder;
    private transient final Timer timer;

    public Reminder(LocalTime time) {
       timer = new Timer();
       TimerCanceler.add(timer);
       this.time = time;
    }
    public Reminder() {
        timer = new Timer();
        isReminder = true;
        TimerCanceler.add(timer);
        this.time = null;
    }

    public void setIsReminder(boolean reminder) {
        isReminder = reminder;
    }

    public boolean getIsReminder() {
        return isReminder;
    }

    public LocalTime getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }




}
