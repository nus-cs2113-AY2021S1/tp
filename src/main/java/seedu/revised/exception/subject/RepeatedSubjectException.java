package seedu.revised.exception.subject;

public class RepeatedSubjectException extends Exception {
    public RepeatedSubjectException(String repeatedSubjectError) {
        super(repeatedSubjectError);
    }
}
