package seedu.commands;

import java.time.LocalTime;
import java.util.Timer;
import seedu.exceptions.InvalidTaskNumberException;


public class Reminder {
    private final LocalTime time;
    private final Timer timer;

    public Reminder(LocalTime time) {
       timer = new Timer();
       this.time = time;
    }
    public Reminder() {
        timer = new Timer();
        this.time = null;
    }

    public LocalTime getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }



}
