package seedu.revised.exception.flashcard;

public class RepeatedFlashcardException extends Exception {
    public RepeatedFlashcardException(String repeatedFlashcardError) {
        super(repeatedFlashcardError);
    }
}
