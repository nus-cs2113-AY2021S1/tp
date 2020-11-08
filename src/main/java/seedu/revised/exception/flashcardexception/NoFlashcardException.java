package seedu.revised.exception.flashcardexception;

public class NoFlashcardException extends Exception {
    public NoFlashcardException(String noFlashcardsError) {
        super(noFlashcardsError);
    }
}
