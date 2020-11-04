package seedu.commands;

import java.util.Timer;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;
import seedu.data.TaskMap;
import java.util.TimerTask;
import java.util.regex.Pattern;
import seedu.ui.Ui;
import java.util.Date;
import java.util.Calendar;
import static seedu.messages.Messages.REMINDER_MESSAGE;

public class Reminder {
    private final Integer time;
    private final Timer timer;

    public Reminder(String timeString) throws InvalidTaskNumberException {
        timer = new Timer();
        if (timeString != null) {
            this.time = Integer.parseInt(timeString);
        } else {
            this.time = null;
        }
    }
    public Reminder() {
        timer = new Timer();
        this.time = null;
    }

    public Integer getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }



}
