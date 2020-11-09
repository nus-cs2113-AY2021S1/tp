package seedu.timetable;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;

import java.util.ArrayList;

public class Timetable {
    public ArrayList<Event> monTimetable;
    public ArrayList<Event> tueTimetable;
    public ArrayList<Event> wedTimetable;
    public ArrayList<Event> thuTimetable;
    public ArrayList<Event> friTimetable;
    public ArrayList<Event> satTimetable;
    public ArrayList<Event> sunTimetable;

    public Timetable() {
        this.monTimetable = new ArrayList<>();
        this.tueTimetable = new ArrayList<>();
        this.wedTimetable = new ArrayList<>();
        this.thuTimetable = new ArrayList<>();
        this.friTimetable = new ArrayList<>();
        this.satTimetable = new ArrayList<>();
        this.sunTimetable = new ArrayList<>();
    }

    public ArrayList<Event> getTimetable(String day) throws WhereGotTimeException {
        switch (day.toLowerCase()) {
        case "mon":
            return monTimetable;
        case "tue":
            return tueTimetable;
        case "wed":
            return wedTimetable;
        case "thu":
            return thuTimetable;
        case "fri":
            return friTimetable;
        case "sat":
            return satTimetable;
        case "sun":
            return sunTimetable;
        default:
            throw new WhereGotTimeException("Sorry! I don't know what day you mean!\n"
                    + "Note: 'day' should be 3-letter, e.g. Mon, TUE, wed, etc.");
        }
    }


}