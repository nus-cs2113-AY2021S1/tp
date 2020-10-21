package seedu.revised.exception.subject;

public class InvalidSubjectException extends Exception {
    public InvalidSubjectException(String invalidSubjectError) {
        super(invalidSubjectError);
    }
}
