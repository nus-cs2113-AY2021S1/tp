package timetable;

public class Message {

    public static final String printSuccessfulClassAddition = "This class has been added successfully! "
            + "\nUse \"show schedule\" to view your timetable.";

    public static final String printSuccessfulActivityAddition = "This activity has been added successfully! "
            + "\nUse \"show schedule\" to view your schedule or use show activity to see in the list form.";

    public static final String printShowSchedule = "This is your schedule for the next 7 days.";
    public static final String printShowLink = "This is the conference link you need for the next two hours.";

    public static final String printInvalidEvent =
            "Sorry you have entered an invalid Timetable Command or your input is in the wrong format!\n"
                    + "Please enter a valid Timetable Command or input \"help\" to find out the correct format!\n";
}
