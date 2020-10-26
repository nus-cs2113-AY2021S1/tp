package seedu.revised.parser;

import seedu.revised.command.flashcard.AddFlashcardCommand;
import seedu.revised.command.flashcard.ExitFlashcardCommand;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.command.flashcard.ListFlashcardCommand;
import seedu.revised.command.flashcard.DeleteFlashcardCommand;
import seedu.revised.command.flashcard.SorryFlashcardCommand;
import seedu.revised.command.flashcard.HelpFlashcardCommand;

public class FlashcardParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static FlashcardCommand parse(String fullCommand) {
        String[] tokens = fullCommand.split(" ");
        String command = tokens[0];

        if (fullCommand.equals("exit")) {
            return new ExitFlashcardCommand();
        } else if (fullCommand.equals("list")) {
            return new ListFlashcardCommand();
        } else if (command.equals("add")) {
            return new AddFlashcardCommand(fullCommand);
        } else if (command.equals("delete")) {
            return new DeleteFlashcardCommand(fullCommand);
        } else if (fullCommand.equals("help")) {
            return new HelpFlashcardCommand();
        } else {
            return new SorryFlashcardCommand();
        }
    }
}
