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
import seedu.duke.data.exception.SystemException.ExceptionType;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;
import static seedu.duke.util.PrefixSyntax.PREFIX_END;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.STRING_SPLIT_DELIMITER;

/**
 * Parses user input.
 */
public class Parser {

    private static final int CONTAINS_TAG_COLOR_INFO = 2;
    private static final int NULL_INDEX = 0;

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

        try {
            switch (commandString.toLowerCase()) {
            case AddCommand.COMMAND_WORD_NOTE:
                return prepareAddNote(userMessage);
            case AddCommand.COMMAND_WORD_EVENT:
                // return prepareAddEvent(userMessage);
            case ListNoteCommand.COMMAND_WORD:
                return prepareListNote(userMessage);
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
            case DeleteCommand.COMMAND_WORD_EVENT:
                // return prepareDeleteEvent(userMessage);
            case FindCommand.COMMAND_WORD:
                return prepareFind(userMessage);
            case PinCommand.COMMAND_WORD:
                // return preparePin(userMessage);
            case CreateTagCommand.COMMAND_WORD:
                return prepareCreateOrDeleteTag(userMessage, true);
            case DeleteTagCommand.COMMAND_WORD:
                return prepareCreateOrDeleteTag(userMessage, false);
            case ListTagCommand.COMMAND_WORD:
                return new ListTagCommand();
            case TagCommand.COMMAND_WORD:
                return prepareTag(userMessage);
            case RemindCommand.COMMAND_WORD:
                // return prepareRemind(userMessage);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            default:
                return new IncorrectCommand(InterfaceManager.LS
                        + "Invalid Command. Please try again or enter help to get a list of valid commands."
                        + InterfaceManager.LS);
            }
        } catch (SystemException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Splits the userMessage into the respective info by the delimiter.
     *
     * @param userMessage Original string of the user message.
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
     * Creates and returns a Tag object based on the info provided.
     *
     * @param tagMessage info of the Tag. Contains tag name and may contain tag color.
     * @return new Tag object.
     * @throws SystemException for missing tag name.
     */
    private Tag handleTagPrefix(String[] tagMessage) throws SystemException {
        String tagName;
        String tagColor = "";

        // Ensures that the message is not blank.
        try {
            if (tagMessage[1].isBlank()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
        }

        // Split into the tag name and tag color.
        String[] tagInfo = tagMessage[1].split(" ", 2);

        if (tagInfo[0].isBlank()) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
        } else {
            tagName = tagInfo[0].trim();
        }
        if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
            tagColor = tagInfo[1].trim();
        }
        return new Tag(tagName, tagColor);
    }

    private Command prepareAddNote(String userMessage) throws SystemException {
        Note note;
        String title = null;
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
                        throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
                    }
                    title = infoDetails[1].trim();
                    break;
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_PIN:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_MISSING_PIN);
                    }
                    isPinned = Boolean.parseBoolean(infoDetails[1].trim());
                    break;
                default:
                    break;
                }
            }

            if (title.isBlank()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
            }

            // Get Content
            System.out.println("Enter Note:");
            content = inputContent();

            // Add to note
            note = tags.isEmpty() ? new Note(title, content, isPinned) : new Note(title, content, isPinned, tags);

            return new AddCommand(note);
        } catch (NullPointerException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE_PREFIX);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
        }
    }

    public String inputContent() {
        Scanner input = new Scanner(System.in);
        StringBuilder commandInput = new StringBuilder();

        // Type note
        do {
            commandInput.append(input.nextLine());
            if (!commandInput.equals(PREFIX_END)) {
                commandInput.append("\n");
            }
            if (commandInput.toString().contains("/d")) {
                deleteLine(commandInput, "\n/d\n", 0);
                deleteLine(commandInput, "\n", 1);
            }
        } while (!commandInput.toString().contains(PREFIX_END));

        deleteLine(commandInput, "\n/end\n", 0);
        return commandInput.toString();
    }

    // Deletes the last line when typing
    public void deleteLine(StringBuilder commandInput, String characters, int noOfChar) {
        int lastChar = commandInput.lastIndexOf(characters) + noOfChar;
        commandInput.delete(lastChar, commandInput.length());
    }

    private Command prepareDeleteNote(String userMessage) throws SystemException {
        int index = NULL_INDEX;

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                switch (prefix) {
                case PREFIX_INDEX:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
                    }
                    index = Integer.parseInt(infoDetails[1].trim());
                    break;
                default:
                }
            }

            if (index <= NULL_INDEX) {
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
            }

            return new DeleteCommand(index, true);
        } catch (NullPointerException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
    }

    /**
     * Ensures that the user does not leave input blank after entering the find command word.
     *
     * @param userMessage user's input of the keyword.
     * @return new FindCommand Command.
     * @throws SystemException for missing keyword.
     */
    private Command prepareFind(String userMessage) throws SystemException {
        if (userMessage.isBlank()) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_KEYWORD);
        }
        return new FindCommand(userMessage);
    }

    /**
     * Returns a ListNote Command.
     * ListNoteCommand is overloaded, so depending on the user input i.e
     * list-n /tag TAG up/down
     * tags and up/down are optional parameters for users to input
     * up/down is to sort the list alphabetically either A-Z or Z-A
     *
     * @param userMessage user's input of the keyword.
     * @return new ListNoteCommand Command.
     */
    private Command prepareListNote(String userMessage) {
        // If no optional parameters, return default display of list note
        if (userMessage == null) {
            return new ListNoteCommand();
        }

        Boolean isAscending = null;
        ArrayList<String> tags = new ArrayList<>();
        String[] words = userMessage.split("\\S");

        // May have multiple tags that need to be accounted for
        if (userMessage.contains("/tag")) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals("/tag")) {
                    tags.add(words[i + 1]);
                }
            }
        }

        for (String word : words) {
            if (word.equals("up")) {
                isAscending = true;
            } else if (word.equals("down")) {
                isAscending = false;
            }
        }

        // No optional parameters case already accounted
        // Minimally if no tag, will have up/down and vice versa
        if (tags.isEmpty()) {
            return new ListNoteCommand(isAscending);
        } else {
            if (isAscending == null) {
                return new ListNoteCommand(tags);
            } else {
                return new ListNoteCommand(isAscending, tags);
            }
        }

    }

    /*
    private Command prepareAddEvent(String userMessage) {
    return new AddCommand(event);
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

    private Command preparePin(String userMessage) {
       return new PinCommand(index);
    }
    */

    /**
     * Returns a CreateTagCommand or a DeleteTagCommand based on the user message.
     *
     * @param userMessage Original string of the user message.
     * @param isCreate determines the type of command (CreateTagCommand or DeleteTagCommand) to return.
     * @return either a new CreateTagCommand or DeleteTagCommand.
     * @throws SystemException for missing tag prefix or tag name.
     */
    private Command prepareCreateOrDeleteTag(String userMessage, boolean isCreate) throws SystemException {
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                if (prefix.equalsIgnoreCase(PREFIX_TAG)) {
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                }
            }
            // Ensures that there is at least 1 tag to be created or deleted.
            if (tags.isEmpty()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
            }
        } catch (NullPointerException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
        }

        if (isCreate) {
            return new CreateTagCommand(tags);
        } else {
            return new DeleteTagCommand(tags);
        }
    }

    /**
     * Returns a TagCommand to tag a note.
     *
     * @param userMessage Original string of the user message.
     * @return a new TagCommand.
     * @throws SystemException for invalid index input, missing tag prefix or tag name.
     */
    private Command prepareTag(String userMessage) throws SystemException {
        int index = NULL_INDEX;
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                switch (prefix) {
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_INDEX:
                    if (infoDetails[1].isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
                    }
                    index = Integer.parseInt(infoDetails[1].trim());
                    break;
                default:
                    break;
                }
            }

            if (tags.isEmpty()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
            }

            if (index <= NULL_INDEX) {
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
            }
        } catch (NullPointerException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
        return new TagCommand(index, tags);
    }

    /*
    private Command prepareRemind(String userMessage) {
       return new RemindCommand(index, isToRemind);
    }
    */
}
