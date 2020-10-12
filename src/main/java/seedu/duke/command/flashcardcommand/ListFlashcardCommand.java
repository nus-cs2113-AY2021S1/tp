package seedu.duke.command.flashcardcommand;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.ui.Ui;

public class ListFlashcardCommand extends FlashcardCommand {
    public Flashcard execute(Topic topic) {
        Ui.printFlashcardList(topic);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
