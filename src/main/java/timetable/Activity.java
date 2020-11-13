package timetable;

public class Activity extends Event {

    public Activity(String activityName, boolean isOnline, String linkOrVenue, Duration duration) {
        super(activityName, isOnline, linkOrVenue, EventType.A);
        super.addPeriod(duration);
        assert super.periods.size() == 1 : "periods size should return 1";
    }

    @Override
    public void addPeriod(Duration duration) {
    }

    @Override
    public String getStorageString() {
        return "|" + periods.get(0).startDateTime.toString()
                + "|" + periods.get(0).endDateTime.toString();
    }
}
