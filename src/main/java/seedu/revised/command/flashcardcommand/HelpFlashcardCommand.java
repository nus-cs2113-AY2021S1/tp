package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class HelpFlashcardCommand extends FlashcardCommand {
    /**
     * Prints help function.
     *
     * @param topic Does nothing in this case but needed since this method was implemented
     *              from an abstract class
     */
    public void execute(Topic topic) {
        Ui.printFlashcardHelp();
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

