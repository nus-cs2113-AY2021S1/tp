package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;

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
