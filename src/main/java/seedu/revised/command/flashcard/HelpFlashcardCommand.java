package seedu.revised.command.flashcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcard.FlashcardCommand;

public class HelpFlashcardCommand extends FlashcardCommand {
    public Flashcard execute(Topic topic) {
        Ui.printFlashcardHelp();
        return null;
    }

    public boolean isExit() {
        return false;
    }
}

