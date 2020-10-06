package seedu.duke.util;

import seedu.duke.command.*;

/**
 * Parses user input.
 */
public class Parser {

    public Command parseCommand(String userInput) {
        String[] userCommandAndArguments = userInput.split(" ", 2);
        String commandString = userCommandAndArguments[0];
        String userMessage;

        try {
            userMessage = userCommandAndArguments[1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            userMessage = null;
        }

        switch  (commandString.toLowerCase()) {
        case AddCommand.COMMAND_WORD_NOTE:
            // return prepareAddNote(userMessage);
        case AddCommand.COMMAND_WORD_EVENT:
            // return prepareAddEvent(userMessage);
        case ListNoteCommand.COMMAND_WORD:
            // return prepareListNote(userMessage);
        case ListEventCommand.COMMAND_WORD_:
            // return prepareListEvent(userMessage);
        case ViewNoteCommand.COMMAND_WORD:
            // return prepareViewNote(userMessage);
        case EditCommand.COMMAND_WORD_NOTE:
            // return prepareEditNote(userMessage);
        case EditCommand.COMMAND_WORD_EVENT:
            // return prepareEditEvent(userMessage);
        case DeleteCommand.COMMAND_WORD_NOTE:
            // return prepareDeleteNote(userMessage);
        case DeleteCommand.COMMAND_WORD_EVENT:
            // return prepareDeleteEvent(userMessage);
        case FindCommand.COMMAND_WORD:
            // return prepareFind(userMessage);
        case PinCommand.COMMAND_WORD:
            // return preparePin(userMessage);
        case CreateTagCommand.COMMAND_WORD:
            // return prepareCreateTag(userMessage);
        case DeleteTagCommand.COMMAND_WORD:
            // return prepareDeleteTag(userMessage);
        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();
        case TagCommand.COMMAND_WORD:
            // return new prepareTag(userMessage);
        case RemindCommand.COMMAND_WORD:
            // return prepareRemind(userMessage);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
        default:
            return new HelpCommand();
        }
    }

    /*
    private Command prepareAddNote(String userMessage) {
        return new AddCommand(note);
    }

    private Command prepareAddEvent(String userMessage) {
        return new AddCommand(event);
    }

    private Command prepareListNote(String userMessage) {
        return new ListNoteCommand();
    }

    private Command prepareListEvent(String userMessage) {
        return new ListEventCommand();
     }

     private Command prepareViewNote(String userMessage) {
        return new ViewNoteCommand();
     }

     private Command prepareEditNote(String userMessage) {
        return new EditCommand(index, note);
     }

     private Command prepareEditEvent(String userMessage) {
        return new EditCommand(index, event);
     }

     private Command prepareDeleteNote(String userMessage) {
        return new EditCommand(index, true);
     }

     private Command prepareDeleteEvent(String userMessage) {
        return new EditCommand(index, false);
     }

     private Command prepareFind(String userMessage) {
        return new FindCommand(keywords);
     }

     private Command preparePin(String userMessage) {
        return new PinCommand(index);
     }

     private Command prepareCreateTag(String userMessage) {
        if (userMessage.isBlank() || userMessage.isEmpty()) {
            return new IncorrectCommand();
        }
        String tagName = ?;
        String tagColor = ?;

        return new CreateTagCommand(tagName, tagColor);
     }

     private Command prepareDeleteTag(String userMessage) {
        if (userMessage.isBlank() || userMessage.isEmpty()) {
            return new IncorrectCommand();
        }
        String tagName = ?;

        return new DeleteTagCommand(tagName);
     }

     private Command prepareTag(String userMessage) {
        if(userMessage.isBlank() || userMessage.isEmpty()) {
            return new IncorrectCommand();
        } else {
            int index = ?
            String tagName = ?
            String tagColor = ?
            Note noteToBeTagged = notebook.getNotes().get(index);
        }
        return new TagCommand(index, tagName, tagColor);
     }

     private Command prepareRemind(String userMessage) {
        return new RemindCommand(index, isToRemind);
     }
     */
}
