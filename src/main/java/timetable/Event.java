package timetable;


import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    public String name;
    public boolean isOnline;
    public String linkOrVenue;
    public List<Duration> periods;
    public EventType eventType;

    public Event(String name, boolean isOnline, String linkOrVenue,EventType eventType) {
        this.name = name;
        this.isOnline = isOnline;
        this.linkOrVenue = linkOrVenue;
        periods = new ArrayList<>();
        this.eventType = eventType;
    }

    public void addPeriod(Duration period) {
        this.periods.add(period);
    }

    public abstract String getStorageString();
}
