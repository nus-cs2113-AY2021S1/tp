package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class ListFlashcardCommand extends FlashcardCommand {
    /**
     * Lists flashcards in the list <code>Topic.flashcards</code>.
     *
     * @param topic the <code>Topic</code> instance of the <code>Topic</code>
     *              class for the user to list flashcards from
     */
    public void execute(Topic topic) {
        Ui.printFlashcardList(topic);
    }

    public boolean isExit() {
        return false;
    }
}
