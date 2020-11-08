package seedu.revised.exception.subjectexception;

public class RepeatedSubjectException extends Exception {
    public RepeatedSubjectException(String repeatedSubjectError) {
        super(repeatedSubjectError);
    }
}
