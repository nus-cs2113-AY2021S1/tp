package seedu.notus.util.parser;

import seedu.notus.command.Command;
import seedu.notus.command.AddNoteCommand;
import seedu.notus.command.AddEventCommand;
import seedu.notus.command.ArchiveNoteCommand;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteNoteCommand;
import seedu.notus.command.DeleteEventCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.command.EditNoteCommand;
import seedu.notus.command.EditEventCommand;
import seedu.notus.command.ExitCommand;
import seedu.notus.command.FindCommand;
import seedu.notus.command.HelpCommand;
import seedu.notus.command.IncorrectCommand;
import seedu.notus.command.ListNoteCommand;
import seedu.notus.command.ListEventCommand;
import seedu.notus.command.ListTagCommand;
import seedu.notus.command.PinCommand;
import seedu.notus.command.RemindCommand;
import seedu.notus.command.TagEventCommand;
import seedu.notus.command.TagNoteCommand;
import seedu.notus.command.UnarchiveNoteCommand;
import seedu.notus.command.ViewNoteCommand;
import seedu.notus.data.exception.SystemException;

public class ParserManager {
    //@@author Chongjx
    /**
     * Parses userInput string into a Command to be executed.
     *
     * @param userInput Original string of the userInput.
     * @return Command to be executed.
     */
    public Command parseCommand(String userInput) {
        String[] userCommandAndArguments = userInput.split(" ", 2);
        String commandString = userCommandAndArguments[0];
        String userMessage;

        try {
            userMessage = userCommandAndArguments[1].trim();

            if (userMessage.isBlank()) {
                userMessage = null;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            userMessage = null;
        }

        try {
            switch (commandString.toLowerCase()) {
            case AddNoteCommand.COMMAND_WORD:
                return new ParseAddNoteCommand(userMessage).parse();
            case AddEventCommand.COMMAND_WORD:
                return new ParseAddEventCommand(userMessage).parse();
            case ArchiveNoteCommand.COMMAND_WORD:
                return new ParseArchiveOrUnarchiveNoteCommand(userMessage, true).parse();
            case UnarchiveNoteCommand.COMMAND_WORD:
                return new ParseArchiveOrUnarchiveNoteCommand(userMessage, false).parse();
            case ListNoteCommand.COMMAND_WORD:
                return new ParseListNoteCommand(userMessage).parse();
            case ListEventCommand.COMMAND_WORD:
                return new ParseListEventCommand(userMessage).parse();
            case ViewNoteCommand.COMMAND_WORD:
                return new ParseViewNoteCommand(userMessage).parse();
            case EditNoteCommand.COMMAND_WORD:
                return new ParseEditNoteCommand(userMessage).parse();
            case EditEventCommand.COMMAND_WORD:
                return new ParseEditEventCommand(userMessage).parse();
            case DeleteNoteCommand.COMMAND_WORD:
                return new ParseDeleteNoteCommand(userMessage).parse();
            case DeleteEventCommand.COMMAND_WORD:
                return new ParseDeleteEventCommand(userMessage).parse();
            case FindCommand.COMMAND_WORD:
                return new ParseFindCommand(userMessage).parse();
            case PinCommand.COMMAND_WORD:
                return new ParsePinCommand(userMessage).parse();
            case CreateTagCommand.COMMAND_WORD:
                return new ParseCreateTagOrDeleteCommand(userMessage, true).parse();
            case DeleteTagCommand.COMMAND_WORD:
                return new ParseCreateTagOrDeleteCommand(userMessage, false).parse();
            case ListTagCommand.COMMAND_WORD:
                return new ListTagCommand();
            case TagNoteCommand.COMMAND_WORD:
                return new ParseTagCommand(userMessage, true).parse();
            case TagEventCommand.COMMAND_WORD:
                return new ParseTagCommand(userMessage, false).parse();
            case RemindCommand.COMMAND_WORD:
                return new RemindCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            default:
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_INVALID_COMMAND);
            }
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }
}
