package seedu.duke.util;

import seedu.duke.command.Command;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ListNoteCommand;
import seedu.duke.command.ListEventCommand;
import seedu.duke.command.ViewNoteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.PinCommand;
import seedu.duke.command.TagCommand;
import seedu.duke.command.RemindCommand;
import seedu.duke.command.ExitCommand;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.timetable.Timetable;

/**
 * Parses user input.
 */
public class Parser {

    public Command parseCommand(String userInput) {
        String[] userCommandAndArguments = userInput.split(" ", 2);
        String commandString = userCommandAndArguments[0];
        //String userMessage = userCommandAndMessage[1];

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
        case TagCommand.COMMAND_WORD:
            // return prepareTag(userMessage);
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

     private Command prepareTag(String userMessage) {
        return new TagCommand();
     }

     private Command prepareRemind(String userMessage) {
        return new RemindCommand(index, isToRemind);
     }
     */
}
