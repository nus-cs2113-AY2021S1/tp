package seedu.duke;

public class Event {
    protected String description;

    public Event(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
