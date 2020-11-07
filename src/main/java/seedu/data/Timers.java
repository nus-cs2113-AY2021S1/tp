package seedu.data;

import java.util.ArrayList;
import java.util.Timer;

public class Timers {
    private static final ArrayList<Timer> timers = new ArrayList<>();

    public static void add(Timer timer) {
        timers.add(timer);
    }

    public static void cancel() {
        for (Timer t : timers) {
            t.cancel();
        }
    }
}
