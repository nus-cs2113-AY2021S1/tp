package seedu.duke.command.repeatexception;

public class InvalidTimeUnitException extends InvalidTypeException {

    public InvalidTimeUnitException(String wrongTimeUnit) {
        super("time unit", wrongTimeUnit, "daily, weekly, monthly");
    }
}
