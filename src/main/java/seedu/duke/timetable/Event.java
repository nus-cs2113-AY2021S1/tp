package seedu.duke.timetable;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    public String name;
    public boolean isOnline;
    public String linkOrVenue;
    public List<Duration> periods;

    public Event(String name, boolean isOnline, String linkOrVenue) {
        this.name = name;
        this.isOnline = isOnline;
        this.linkOrVenue = linkOrVenue;
        periods = new ArrayList<>();
    }

    public void addPeriod(Duration period) {
        this.periods.add(period);
    }
}
