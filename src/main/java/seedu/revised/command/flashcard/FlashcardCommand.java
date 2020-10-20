package seedu.revised.command.flashcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.flashcard.*;

public class FlashcardCommand {
    public Flashcard execute(Topic topic) throws FailedParseException, NoFlashcardException, RepeatedFlashcardException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}