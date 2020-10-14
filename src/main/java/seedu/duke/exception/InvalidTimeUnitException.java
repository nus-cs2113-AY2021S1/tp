package seedu.duke.exception;


public class InvalidTimeUnitException extends DukeException {

    public InvalidTimeUnitException(String wrongTimeUnit) {
        super(wrongTimeUnit + " is not a valid time unit. Valid types are: daily, weekly, monthly");
    }
}
