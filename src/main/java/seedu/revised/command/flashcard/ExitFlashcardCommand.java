package seedu.revised.command.flashcard;

public class ExitFlashcardCommand extends FlashcardCommand {
    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
