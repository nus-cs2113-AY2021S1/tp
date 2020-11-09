package seedu.revised.exception.subjectexception;

public class InvalidSubjectException extends Exception {
    public InvalidSubjectException(String invalidSubjectError) {
        super(invalidSubjectError);
    }
}
