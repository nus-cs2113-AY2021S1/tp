package seedu.revised.exception.flashcardexception;

public class RepeatedFlashcardException extends Exception {
    public RepeatedFlashcardException(String repeatedFlashcardError) {
        super(repeatedFlashcardError);
    }
}
