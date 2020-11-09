package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.ui.Ui;

public class SorryFlashcardCommand extends FlashcardCommand {

    /**
     * Throws an exception to show invalid user input.
     *
     * @param topic                 Does nothing in this case but needed since this method was implemented
     *                              from an abstract class
     * @throws FailedParseException If user input is invalid
     */
    public void execute(Topic topic) throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
