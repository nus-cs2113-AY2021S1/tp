package seedu.revised.parser;

import seedu.revised.command.flashcardcommand.AddFlashcardCommand;
import seedu.revised.command.flashcardcommand.ExitFlashcardCommand;
import seedu.revised.command.flashcardcommand.FlashcardCommand;
import seedu.revised.command.flashcardcommand.ListAllFlashcardCommand;
import seedu.revised.command.flashcardcommand.ListFlashcardCommand;
import seedu.revised.command.flashcardcommand.DeleteFlashcardCommand;
import seedu.revised.command.flashcardcommand.SorryFlashcardCommand;
import seedu.revised.command.flashcardcommand.HelpFlashcardCommand;

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
        } else if (fullCommand.equals("list all")) {
            return new ListAllFlashcardCommand();
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
