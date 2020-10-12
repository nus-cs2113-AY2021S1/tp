package seedu.duke.timetable;

public class Lesson extends Event {


    public Lesson(String moduleCode, String linkOrVenue, boolean isOnline) {
        super(moduleCode, isOnline,linkOrVenue);
    }

}
