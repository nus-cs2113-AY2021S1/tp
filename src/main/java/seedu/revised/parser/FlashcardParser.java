package seedu.revised.parser;

import seedu.revised.command.flashcard.AddFlashcardCommand;
import seedu.revised.command.flashcard.ExitFlashcardCommand;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.command.flashcard.ListFlashcardCommand;
import seedu.revised.command.flashcard.DeleteFlashcardCommand;
import seedu.revised.command.flashcard.SorryFlashcardCommand;

public class FlashcardParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static FlashcardCommand parse(String fullCommand) {
        if (fullCommand.equals("exit")) {
            return new ExitFlashcardCommand();
        } else if (fullCommand.equals("list")) {
            return new ListFlashcardCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddFlashcardCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteFlashcardCommand(fullCommand);
        } else {
            return new SorryFlashcardCommand();
        }
    }
}
