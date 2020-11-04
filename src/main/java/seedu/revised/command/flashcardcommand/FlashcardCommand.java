package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcardexception.InvalidFlashcardException;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.exception.flashcardexception.RepeatedFlashcardException;

public abstract class FlashcardCommand {
    public abstract void execute(Topic topic) throws NoFlashcardException, RepeatedFlashcardException,
            InvalidFlashcardException, FailedParseException;

    public boolean isExit() {
        return false;
    }
}