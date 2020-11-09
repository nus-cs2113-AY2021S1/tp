package seedu.task;

public class Event extends Task {

    public Event(String description, String location, String timeStart, String timeEnd) {
        super(description, location, timeStart, timeEnd);
    }

    @Override
    public String toString() {
        return description + " " + location + " " + timeStart + "-" + timeEnd;
    }
}
