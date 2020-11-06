package seedu.revised.exception.topicexception;

public class RepeatedTopicException extends Exception {
    public RepeatedTopicException(String repeatedTopicError) {
        super(repeatedTopicError);
    }
}
