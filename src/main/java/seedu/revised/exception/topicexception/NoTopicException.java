package seedu.revised.exception.topicexception;

public class NoTopicException extends Exception {
    public NoTopicException(String noTopicsError) {
        super(noTopicsError);
    }
}
