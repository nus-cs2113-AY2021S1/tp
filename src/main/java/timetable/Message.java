package timetable;

public class Message {

    public static final String printSuccessfulClassAddition = "This class has been added successfully! "
            + "Use 'show schedule' to view your timetable.";


    public static final String printShowSchedule = "This is your schedule for the next 7 days.";
    public static final String printShowLink = "This is the conference link u need for the next two hour.";

    public static final String printInvalidEvent = "Invalid event!"
            + "Please use this format to add events: add class /MODULE CODE"
            + "/ONLINE|OFFLINE/ZOOM LINK|VENUE/DAYS at TIME/NUMBER OF WEEKS";
}
