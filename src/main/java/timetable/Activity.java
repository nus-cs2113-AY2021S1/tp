package timetable;

public class Activity extends Event {

    public Activity(String activityName, boolean isOnline, String linkOrVenue, Duration duration) {
        super(activityName, isOnline, linkOrVenue, EventType.A);
        super.addPeriod(duration);
    }

    @Override
    public void addPeriod(Duration duration) {
    }

    @Override
    public String getStorageString() {
        return null;
    }
}
