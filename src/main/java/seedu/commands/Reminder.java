package seedu.commands;

import java.time.LocalTime;
import java.util.Timer;

import seedu.data.Timers;



public class Reminder {
    private final LocalTime time;
    private transient final Timer timer;

    public Reminder(LocalTime time) {
       timer = new Timer();
       Timers.add(timer);
       this.time = time;
    }

    public Reminder() {
        timer = new Timer();
        Timers.add(timer);
        this.time = null;
    }

    public LocalTime getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }




}
