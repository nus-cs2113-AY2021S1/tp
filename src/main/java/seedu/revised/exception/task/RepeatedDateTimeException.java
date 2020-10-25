package seedu.revised.exception.task;

public class RepeatedDateTimeException extends Exception {
    public RepeatedDateTimeException(String printDateTimeError) {
        super(printDateTimeError);
    }
}
