package seedu.commands;

import java.time.LocalTime;
import java.util.Timer;

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




}
