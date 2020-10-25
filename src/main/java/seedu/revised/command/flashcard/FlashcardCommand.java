package seedu.revised.command.flashcard;

import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;

public abstract class FlashcardCommand {
    public abstract void execute(Topic topic) throws FailedParseException,
            NoFlashcardException, RepeatedFlashcardException;

    public boolean isExit() {
        return false;
    }
}