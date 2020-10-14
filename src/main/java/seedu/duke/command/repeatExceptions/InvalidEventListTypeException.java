package seedu.duke.command.repeatExceptions;

public class InvalidEventListTypeException extends InvalidTypeException {

    public InvalidEventListTypeException (String wrongEvent) {
        super("event type", wrongEvent, "personal, timetable, zoom");
    }
}
