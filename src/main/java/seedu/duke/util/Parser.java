package seedu.duke.util;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.PinCommand;
import seedu.duke.command.ListNoteCommand;
import seedu.duke.command.ViewNoteCommand;
import seedu.duke.command.CreateTagCommand;
import seedu.duke.command.DeleteTagCommand;
import seedu.duke.command.ListTagCommand;
import seedu.duke.command.TagCommand;
import seedu.duke.command.ListEventCommand;
import seedu.duke.command.RemindCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ExitCommand;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;
import static seedu.duke.util.PrefixSyntax.PREFIX_END;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.duke.util.PrefixSyntax.STRING_NEW_LINE;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.STRING_SPLIT_DELIMITER;

/**
 * Parses user input.
 */
public class Parser {

    private static final int CONTAINS_TAG_COLOR_INFO = 2;

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
            userMessage = userCommandAndArguments[1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            userMessage = null;
        }

        switch (commandString.toLowerCase()) {
        case AddCommand.COMMAND_WORD_NOTE:
            return prepareAddNote(userMessage);
        /*case AddCommand.COMMAND_WORD_EVENT:
             return prepareAddEvent(userMessage);*/
        case ListNoteCommand.COMMAND_WORD:
            // return prepareListNote(userMessage);
        case ListEventCommand.COMMAND_WORD:
            // return prepareListEvent(userMessage);
        case ViewNoteCommand.COMMAND_WORD:
            // return prepareViewNote(userMessage);
        case EditCommand.COMMAND_WORD_NOTE:
            // return prepareEditNote(userMessage);
        case EditCommand.COMMAND_WORD_EVENT:
            // return prepareEditEvent(userMessage);
        case DeleteCommand.COMMAND_WORD_NOTE:
            return prepareDeleteNote(userMessage);
        /*case DeleteCommand.COMMAND_WORD_EVENT:
             return prepareDeleteEvent(userMessage);*/
        case FindCommand.COMMAND_WORD:
            // return prepareFind(userMessage);
        case PinCommand.COMMAND_WORD:
            // return preparePin(userMessage);
        case CreateTagCommand.COMMAND_WORD:
            return prepareCreateTag(userMessage);
        case DeleteTagCommand.COMMAND_WORD:
            return prepareDeleteTag(userMessage);
        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();
        case TagCommand.COMMAND_WORD:
            return prepareTag(userMessage);
        case RemindCommand.COMMAND_WORD:
            // return prepareRemind(userMessage);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
        default:
            return new HelpCommand();
        }
    }

    /**
     * Splits the userMessage into the respective info by the delimiter.
     *
     * @param userMessage Original string of the userInput.
     * @return Split strings.
     * @throws NullPointerException when the userMessage is empty.
     */
    private ArrayList<String[]> splitInfoDetails(String userMessage) throws NullPointerException {
        String[] splitMessage = userMessage.split(PREFIX_DELIMITER);
        ArrayList<String[]> splitMessageContent = new ArrayList<>();

        // Splits the prefix and the remaining content
        for (String s : splitMessage) {
            splitMessageContent.add(s.split(STRING_SPLIT_DELIMITER, 2));
        }

        // Remove the first element as it is always empty
        splitMessageContent.remove(0);
        return splitMessageContent;
    }

    /**
     * Prepare userInput into Note before adding into Notebook.
     *
     * @param userMessage Original string user inputs.
     * @return Result of the add note command
     */
    private Command prepareAddNote(String userMessage) {
        Note note;
        String title = "";
        String content;
        boolean isPinned = false;
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                switch (prefix) {
                case PREFIX_TITLE:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    title = infoDetails[1].trim();
                    break;
                case PREFIX_TAG:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    Tag tag;
                    String[] tagInfo = infoDetails[1].split(" ", 2);

                    if (tagInfo[0].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    } else if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
                        tag = new Tag(tagInfo[0].trim(), tagInfo[1].trim());
                    } else {
                        tag = new Tag(tagInfo[0].trim());
                    }
                    tags.add(tag);
                    break;
                case PREFIX_PIN:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    isPinned = Boolean.parseBoolean(infoDetails[1].trim());
                    break;
                default:
                    break;
                }
            }

            if (title.isBlank()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_TITLE);
            }

            // Get Content
            System.out.println("Enter Note:");
            content = inputContent();

            // Add to note
            note = tags.isEmpty() ? new Note(title, content, isPinned) : new Note(title, content, isPinned, tags);

            return new AddCommand(note);
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            return new IncorrectCommand("Missing description!");
        }
    }

    /**
     * Used for input of note content and processing the input into a readable data.
     *
     * @return A string of converted content input
     */
    public String inputContent() {
        Scanner input = new Scanner(System.in);
        StringBuilder commandInput = new StringBuilder();

        // Type note
        do {
            commandInput.append(input.nextLine());

            // Add next line when user press enter
            if (!commandInput.toString().equals(PREFIX_END)) {
                commandInput.append(STRING_NEW_LINE);
            }

            // "/d" Delete previous line if there user makes mistakes
            if (commandInput.toString().contains(PREFIX_DELETE_LINE)) {
                deleteLine(commandInput, STRING_NEW_LINE + PREFIX_DELETE_LINE + STRING_NEW_LINE, 0);
                deleteLine(commandInput, STRING_NEW_LINE, 1);
            }
        } while (!commandInput.toString().contains(PREFIX_END)); // "/end" to end input note

        // Delete "/end" command when user ends the edit
        deleteLine(commandInput, STRING_NEW_LINE + PREFIX_END + STRING_NEW_LINE, 0);

        return commandInput.toString();
    }

    /**
     * Delete the last line for mistakes made in inputContent().
     *
     * @param commandInput Original string of the note content.
     * @param characters String of character to be removed.
     * @param noOfChar Number of character. 0 to remove new line, 1 to resume typing on the same line.
     */
    public void deleteLine(StringBuilder commandInput, String characters, int noOfChar) {
        int lastChar = commandInput.lastIndexOf(characters) + noOfChar;
        commandInput.delete(lastChar, commandInput.length());
    }

    /**
     * Prepare userInput into a int before deletion.
     *
     * @param userMessage Original string user inputs.
     * @return Result of the delete note command
     */
    private Command prepareDeleteNote(String userMessage) {
        int index = 0;
        String title = "";
        String prefix = "";

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                prefix = infoDetails[0];
                switch (prefix) {
                case PREFIX_INDEX:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    index = Integer.parseInt(infoDetails[1].trim());
                    break;
                case PREFIX_TITLE:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    title = infoDetails[1].trim();
                    break;
                default:
                }
            }
            if (prefix.equalsIgnoreCase(PREFIX_TITLE)) {
                return new DeleteCommand(title);
            } else {
                return new DeleteCommand(index);
            }
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        } catch (NumberFormatException exception) {
            return new IncorrectCommand("Invalid format");
        }
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

    private Command prepareDeleteEvent(String userMessage) {
       return new EditCommand(index, false);
    }

    private Command prepareFind(String userMessage) {
       return new FindCommand(keywords);
    }

    private Command preparePin(String userMessage) {
       return new PinCommand(index);
    }
    */

    private Command prepareCreateTag(String userMessage) {
        String tagName;
        String tagColor = "";

        try {
            String[] tagInfo = userMessage.split(" ", 2);

            if (tagInfo[0].isBlank()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
            } else {
                tagName = tagInfo[0].trim();
            }
            if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
                tagColor = tagInfo[1].trim();
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
            return new IncorrectCommand("Missing description!");
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
        return new CreateTagCommand(tagName, tagColor);
    }

    private Command prepareDeleteTag(String userMessage) {
        String tagName;

        try {
            if (userMessage.isBlank()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
            } else {
                tagName = userMessage.trim();
            }
        } catch (NullPointerException exception) {
            return new IncorrectCommand("Missing description!");
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
        return new DeleteTagCommand(tagName);
    }

    private Command prepareTag(String userMessage) {
        int index = 0;
        String tagName = "";
        String tagColor = "";

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                switch (prefix) {
                case PREFIX_TAG:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }

                    String[] tagInfo = infoDetails[1].split(" ", 2);

                    if (tagInfo[0].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    } else {
                        tagName = tagInfo[0].trim();
                    }
                    if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
                        tagColor = tagInfo[1].trim();
                    }
                    break;
                case PREFIX_INDEX:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
                    }
                    index = Integer.parseInt(infoDetails[1].trim());
                    break;
                default:
                }
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
            return new IncorrectCommand("Missing description!");
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        } catch (NumberFormatException exception) {
            return new IncorrectCommand("Invalid format!");
        }
        return new TagCommand(index, tagName, tagColor);
    }

    /*
    private Command prepareRemind(String userMessage) {
       return new RemindCommand(index, isToRemind);
    }
    */
}
