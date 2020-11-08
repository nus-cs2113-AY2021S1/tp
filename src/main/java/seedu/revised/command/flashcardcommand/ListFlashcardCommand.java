package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class ListFlashcardCommand extends FlashcardCommand {
    public void execute(Topic topic) {
        Ui.printFlashcardList(topic);
    }

    public boolean isExit() {
        return false;
    }
}
