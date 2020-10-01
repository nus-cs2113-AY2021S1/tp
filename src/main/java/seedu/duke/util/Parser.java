package seedu.duke.util;

import seedu.duke.command.Command;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ViewNoteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.PinCommand;
import seedu.duke.command.TagCommand;
import seedu.duke.command.RemindCommand;
import seedu.duke.command.ExitCommand;

/**
 * Parses user input.
 */
public class Parser {

    public Command parseCommand(String userInput) {
        String commandString = userInput.split(" ", 1)[0];

        switch  (commandString.toLowerCase()) {
        case AddCommand.COMMAND_WORD_NOTE:
        case AddCommand.COMMAND_WORD_EVENT:
            // return new AddCommand();
        case ListCommand.COMMAND_WORD:
            // return new ListCommand();
        case ViewNoteCommand.COMMAND_WORD:
            // return new ViewNoteCommand();
        case EditCommand.COMMAND_WORD_NOTE:
        case EditCommand.COMMAND_WORD_EVENT:
            // return new EditCommand();
        case DeleteCommand.COMMAND_WORD_NOTE:
        case DeleteCommand.COMMAND_WORD_EVENT:
            // return new DeleteCommand();
        case FindCommand.COMMAND_WORD:
            // return new FindCommand();
        case PinCommand.COMMAND_WORD:
            // return new PinCommand();
        case TagCommand.COMMAND_WORD:
            // return new TagCommand();
        case RemindCommand.COMMAND_WORD:
            // return new RemindCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
        default:
            return new HelpCommand();
        }
    }
}
