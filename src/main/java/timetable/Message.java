package timetable;

public class Message {

    public static final String printSuccessfulClassAddition = "This class has been added successfully! "
            + "\nUse \"show schedule\" to view your timetable.";

    public static final String printSuccessfulActivityAddition = "This activity has been added successfully! "
            + "\nUse \"show schedule\" to view your timetable.";

    public static final String printShowSchedule = "This is your schedule for the next 7 days.";
    public static final String printShowLink = "This is the conference link you need for the next two hours.";

    public static final String printInvalidEvent = "Invalid command!\n\n"
            + "Use \"add class\" or \"add activity\" to start adding items\n"
            + "into your timetable or \"show schedule\" to check your timetable";
}
