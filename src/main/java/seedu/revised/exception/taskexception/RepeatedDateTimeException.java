package seedu.revised.exception.taskexception;

public class RepeatedDateTimeException extends Exception {
    public RepeatedDateTimeException(String printDateTimeError) {
        super(printDateTimeError);
    }
}
