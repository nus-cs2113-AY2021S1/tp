package seedu.revised.command.flashcard;

import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;

public class ExitFlashcardCommand extends FlashcardCommand {
    @Override
    public void execute(Topic topic) {

    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
