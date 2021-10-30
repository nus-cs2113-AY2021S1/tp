package timetable;

public class Lesson extends Event {

    public int numPerWeek;

    public Lesson(String moduleCode, String linkOrVenue, boolean isOnline, int numPerWeek) {
        super(moduleCode, isOnline,linkOrVenue,EventType.L);
        this.numPerWeek = numPerWeek;
    }

    @Override
    public String getStorageString() {
        String storageString = "|" + numPerWeek + "|" + super.periods.size();
        for (Duration period : super.periods) {
            storageString = String.format("%s|%s", storageString, period.startDateTime.toString());
            storageString = String.format("%s|%s", storageString, period.endDateTime.toString());
        }
        return storageString;
    }
}
