package seedu.revised.exception.topic;

public class RepeatedTopicException extends Exception {
    public RepeatedTopicException(String repeatedTopicError) {
        super(repeatedTopicError);
    }
}
