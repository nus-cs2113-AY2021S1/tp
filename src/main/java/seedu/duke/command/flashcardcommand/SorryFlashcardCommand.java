package seedu.duke.command.flashcardcommand;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.ui.Ui;

public class SorryFlashcardCommand extends FlashcardCommand {

    public Flashcard execute(Topic topic) {
        Ui.printError();
        return null;
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
