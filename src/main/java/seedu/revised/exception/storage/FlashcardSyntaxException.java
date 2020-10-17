package seedu.revised.exception.storage;

public class FlashcardSyntaxException extends Exception {
    public FlashcardSyntaxException(String errorMessage) {
        super(errorMessage);
    }

    public FlashcardSyntaxException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
