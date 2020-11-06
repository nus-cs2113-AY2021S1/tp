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
        fullCommand = fullCommand.trim();
        String[] tokens = fullCommand.split(" ");
        String fullCommandLowerCase = fullCommand.toLowerCase();
        String command = tokens[0].toLowerCase();

        switch (command) {
        case "add":
            return new AddFlashcardCommand(fullCommand);
        case "delete":
            return new DeleteFlashcardCommand(fullCommand);
        case "help":
            return new HelpFlashcardCommand();
        default:
            switch (fullCommandLowerCase) {
            case "exit":
                return new ExitFlashcardCommand();
            case "list" :
                return new ListFlashcardCommand();
            case "list all":
                return new ListAllFlashcardCommand();
            default:
                return new SorryFlashcardCommand();
            }
        }
    }
}
