package seedu.revised.command.flashcard;

import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class HelpFlashcardCommand extends FlashcardCommand {
    public void execute(Topic topic) {
        Ui.printFlashcardHelp();
    }

    public boolean isExit() {
        return false;
    }
}

