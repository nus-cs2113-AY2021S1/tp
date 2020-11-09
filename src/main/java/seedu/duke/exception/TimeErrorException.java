package seedu.duke.exception;

public class TimeErrorException extends DukeException {
    public TimeErrorException() {
        super("Something is wrong with the time!" + System.lineSeparator()
                + "The accepted formats are:" + System.lineSeparator()
                + "(12 hour) hh:mm am/pm, hhmm am/pm, hh am/pm or " + System.lineSeparator()
                + "(24 hour) HH:mm, HHmm, HH.");
    }
}
