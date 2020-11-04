package seedu.revised.exception.topicexception;

public class InvalidTopicException extends Exception {
    public InvalidTopicException(String enterTopicError) {
        super(enterTopicError);
    }
}
