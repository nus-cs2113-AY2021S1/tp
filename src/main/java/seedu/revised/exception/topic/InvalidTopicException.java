package seedu.revised.exception.topic;

public class InvalidTopicException extends Exception {
    public InvalidTopicException(String enterTopicError) {
        super(enterTopicError);
    }
}
