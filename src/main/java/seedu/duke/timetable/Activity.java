package seedu.duke.timetable;

public class Activity extends Event {

    public Activity(String activityName, boolean isOnline, String linkOrVenue, Duration duration) {
        super(activityName, isOnline, linkOrVenue);
        super.addPeriod(duration);
    }

    @Override
    public void addPeriod(Duration duration) {

    }
}
