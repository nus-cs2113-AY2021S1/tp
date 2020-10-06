package seedu.duke.util;

import seedu.duke.command.Command;
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
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.timetable.Timetable;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;

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

        switch (commandString.toLowerCase()) {
        case AddCommand.COMMAND_WORD_NOTE:
            return prepareAddNote(userMessage);
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


    private Command prepareAddNote(String userMessage) {
        Note note;
        String title = null;
        String content;
        Boolean isPinned = false;
        ArrayList<Tag> tags = new ArrayList<Tag>();
        ArrayList<String[]> individualInfo = new ArrayList<String[]>();
        Tag tag;
        String[] userMessageArray;

        try {
            userMessageArray = userMessage.split("/");
            for (String type : userMessageArray) {
                individualInfo.add(type.split(" ", 2));
            }
            individualInfo.remove(0);

            for (String[] stringInfo : individualInfo) {
                switch (stringInfo[0]) {
                case PREFIX_TITLE:
                    if (stringInfo[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    title = stringInfo[1].trim();
                    break;
                case PREFIX_TAG:
                    if (stringInfo[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    String[] arrayTags = stringInfo[1].replaceAll("\\s", "").split(",");
                    for (String itemTag : arrayTags) {
                        tag = new Tag(itemTag);
                        tags.add(tag);
                    }
                    break;
                case PREFIX_PIN:
                    if (stringInfo[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    isPinned = Boolean.parseBoolean(stringInfo[1].trim());
                    break;
                default:
                }
            }

            // Get Content
            System.out.println("Enter Note:");
            content = inputContent();

            // Add to note
            note = tags.isEmpty() ? new Note(title, content, isPinned) : new Note(title, content, isPinned, tags);

            return new AddCommand(note);
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new IncorrectCommand("Missing description");
        }
    }

    // add-n t[C++ to Java]
    // add-n pin[true] t[C++ to Java] tag[CS2113]
    // add-n /t C++ to Java /pin true /tag CS2113, Computing

    public String inputContent() {
        Scanner input = new Scanner(System.in);
        StringBuilder commandInput = new StringBuilder();

        // Type note
        do {
            commandInput.append(input.nextLine());
            if (!commandInput.equals("/end")) {
                commandInput.append("\n");
            }
            if (commandInput.toString().contains("/d")) {
                deleteLine(commandInput, "\n/d\n", 0);
                deleteLine(commandInput, "\n", 1);
            }
        } while (!commandInput.toString().contains("/end"));

        deleteLine(commandInput, "\n/end\n", 0);
        return commandInput.toString();
    }

    // Delete the last line when typing
    public void deleteLine(StringBuilder commandInput, String characters, int noOfChar) {
        int lastChar = commandInput.lastIndexOf(characters) + noOfChar;
        commandInput.delete(lastChar, commandInput.length());
    }

    /*
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
