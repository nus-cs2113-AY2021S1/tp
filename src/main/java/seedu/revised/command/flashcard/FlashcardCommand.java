package seedu.revised.command.flashcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.card.NoSubjectException;
import seedu.revised.exception.card.RepeatedSubjectException;

public class FlashcardCommand {
    public Flashcard execute(Topic topic) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}