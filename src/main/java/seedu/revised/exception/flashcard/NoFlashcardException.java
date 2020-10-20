package seedu.revised.exception.flashcard;

public class NoFlashcardException extends Exception {
    public NoFlashcardException(String noFlashcardsError) {
        super(noFlashcardsError);
    }
}
