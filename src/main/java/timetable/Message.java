package timetable;

public class Message {

    public static final String printSuccessfulClassAddition = "This class has been added successfully! " +
                "Use 'show schedule' to view your timetable.\n";

//    public static final String printSuccessfulEventAddition(Event event) {
//        return "This " + event.eventType + " has been added successfully! " +
//                "Use 'show schedule' to view your timetable.\n";
//    }
//
//    public static String printTimetableClash(Event event){
//        return "Oh no! This " + event.eventType + " clashes with another event in your timetable.\n";
//        // todo: add description of clashing event
//    }

    public static final String printShowSchedule = "This is your schedule for the next 7 days.";

    public static final String printInvalidEvent = "Invalid event!" +
            "Please use this format to add events: add class /MODULE CODE/ONLINE|OFFLINE/ZOOM LINK|VENUE/DAYS at TIME/NUMBER OF WEEKS";

}
