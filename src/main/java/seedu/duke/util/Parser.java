package seedu.duke.util;

import seedu.duke.command.Command;
import seedu.duke.command.AddNoteCommand;
import seedu.duke.command.AddEventCommand;
import seedu.duke.command.CreateTagCommand;
import seedu.duke.command.DeleteNoteCommand;
import seedu.duke.command.DeleteEventCommand;
import seedu.duke.command.DeleteTagCommand;
import seedu.duke.command.EditNoteCommand;
import seedu.duke.command.EditEventCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.ListNoteCommand;
import seedu.duke.command.ListEventCommand;
import seedu.duke.command.ListTagCommand;
import seedu.duke.command.PinCommand;
import seedu.duke.command.RemindCommand;
import seedu.duke.command.TagCommand;
import seedu.duke.command.ViewNoteCommand;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.exception.SystemException.ExceptionType;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.timetable.Event;
import seedu.duke.data.timetable.RecurringEvent;
import seedu.duke.data.timetable.DailyEvent;
import seedu.duke.data.timetable.WeeklyEvent;
import seedu.duke.data.timetable.MonthlyEvent;
import seedu.duke.data.timetable.YearlyEvent;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_END;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_STOP_RECURRING;
import static seedu.duke.util.PrefixSyntax.STRING_NEW_LINE;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.STRING_SPLIT_DELIMITER;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

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
            case AddNoteCommand.COMMAND_WORD:
                return prepareAddNote(userMessage);
            case AddEventCommand.COMMAND_WORD:
                return prepareAddEvent(userMessage);
            case ListNoteCommand.COMMAND_WORD:
                return prepareListNote(userMessage);
            case ListEventCommand.COMMAND_WORD:
                return prepareListEvent(userMessage);
            case ViewNoteCommand.COMMAND_WORD:
                // return prepareViewNote(userMessage);
            case EditNoteCommand.COMMAND_WORD:
                // return prepareEditNote(userMessage);
            case EditEventCommand.COMMAND_WORD:
                // return prepareEditEvent(userMessage);
            case DeleteNoteCommand.COMMAND_WORD:
                return prepareDeleteNote(userMessage);
            case DeleteEventCommand.COMMAND_WORD:
                return prepareDeleteEvent(userMessage);
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
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_COMMAND);
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
    private ArrayList<String[]> splitInfoDetails(String userMessage) throws SystemException {
        try {
            String[] splitMessage = userMessage.split(PREFIX_DELIMITER);
            ArrayList<String[]> splitMessageContent = new ArrayList<>();

            // Splits the prefix and the remaining content
            for (String s : splitMessage) {
                splitMessageContent.add(s.split(STRING_SPLIT_DELIMITER, 2));
            }

            // Remove the first element as it is always empty
            splitMessageContent.remove(0);
            return splitMessageContent;
        } catch (NullPointerException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND);
        }
    }

    /**
     * Checks if an input string if blank. If it is, throw the provided system exception. If it is not, return that
     * string trimmed.
     *
     * @param input Input to be checked.
     * @param exceptionType ExceptionType to be thrown.
     * @return Trimmed non-blank string.
     * @throws SystemException Occurs when input is blank.
     */
    private String checkBlank(String input, SystemException.ExceptionType exceptionType) throws SystemException {
        if (input.isBlank()) {
            throw new SystemException(exceptionType);
        } else {
            return input.trim();
        }
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
        String tagsInfo;

        // Ensures that the message is not blank.
        try {
            tagsInfo = checkBlank(tagMessage[1], ExceptionType.EXCEPTION_MISSING_TAG);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
        }

        // Split into the tag name and tag color.
        String[] tagInfo = tagsInfo.split(" ", 2);

        tagName = checkBlank(tagInfo[0], ExceptionType.EXCEPTION_MISSING_TAG);

        if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
            tagColor = tagInfo[1].trim();
        }
        return new Tag(tagName, tagColor);
    }

    /**
     * Prepare userInput into Note before adding into Notebook.
     *
     * @param userMessage Original string user inputs.
     * @return Result of the add note command.
     * @throws SystemException if an error occurs.
     */
    private Command prepareAddNote(String userMessage) throws SystemException {
        Note note;
        String title = "";
        String content = "";
        boolean isPinned = false;
        boolean isInputSuccess = false;
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    title = checkBlank(infoDetails[1], exception);
                    break;
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_PIN:
                    exception = ExceptionType.EXCEPTION_MISSING_PIN;
                    isPinned = Boolean.parseBoolean(checkBlank(infoDetails[1], exception));
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_WRONG_PREFIX);
                }
            }

            title = checkBlank(title, ExceptionType.EXCEPTION_MISSING_TITLE);

            // Get Content
            do {
                System.out.println("Enter Note:");
                try {
                    content = inputContent();
                    isInputSuccess = true;
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(ExceptionType.EXCEPTION_INVALID_END_INPUT);
                }
            } while (!isInputSuccess);

            // Add to note
            note = tags.isEmpty() ? new Note(title, content, isPinned) : new Note(title, content, isPinned, tags);

            return new AddNoteCommand(note);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
        }
    }

    /**
     * Takes a user string designated to add an event and prepares it by extracting relevant information from the
     * provided required and optional tags. It requires a title tag and a timing tag (/t and /timing). Other tags allow
     * for it to be recurring and to set reminders of the event.
     * Takes the format "add-e /t {Title} /timing {YYYY-MM-DD HH:MM} [/rem [How much earlier to remind]]
     * [/rec {How often to re-occur}] [/stop {YYYY-MM-DD HH:MM}]
     *
     * @param userMessage User input message.
     * @return Returns an AddEventCommand to be executed by Duke.
     * @throws SystemException Information provided by the tags are blank, wrong or do not have a default value.
     */
    private Command prepareAddEvent(String userMessage) throws SystemException {
        // add-e eventTitle /t timing /rec occurrence /rem time before (default same day)

        String title = "";
        LocalDateTime dateTime = null;
        LocalDateTime recurringEndTime = null;
        boolean toRemind = false;
        boolean isRecurring = false;
        String recurringType = "";

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);
            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    title = checkBlank(infoDetails[1], exception);
                    break;
                case PREFIX_TIMING:
                    exception = ExceptionType.EXCEPTION_MISSING_TIMING;
                    String timingString = checkBlank(infoDetails[1], exception);
                    dateTime = DateTimeManager.dateTimeParser(timingString);
                    break;
                case PREFIX_REMIND:
                    toRemind = true;
                    break;
                case PREFIX_RECURRING:
                    isRecurring = true;
                    exception = ExceptionType.EXCEPTION_MISSING_RECURRING_TYPE;
                    try {
                        recurringType = checkBlank(infoDetails[1], exception).toLowerCase();
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        recurringType = RecurringEvent.DAILY_RECURRENCE_TYPE;
                    }
                    break;
                case PREFIX_STOP_RECURRING:
                    exception = ExceptionType.EXCEPTION_MISSING_RECURRING_END_TIME;
                    String endTimingString = checkBlank(infoDetails[1], exception);
                    recurringEndTime = DateTimeManager.dateTimeParser(endTimingString);
                    break;
                default:
                    throw new SystemException(SystemException.ExceptionType.EXCEPTION_WRONG_PREFIX);
                }
            }
            title = checkBlank(title, ExceptionType.EXCEPTION_MISSING_TITLE);

            if (dateTime == null) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_MISSING_TIMING);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
        }

        Event event;

        if (isRecurring) {
            LocalDate date = (recurringEndTime != null) ? recurringEndTime.toLocalDate() : null;
            switch (recurringType) {
            case RecurringEvent.DAILY_RECURRENCE_TYPE:
                event = new DailyEvent(title, dateTime, toRemind, date);
                break;
            case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
                event = new WeeklyEvent(title, dateTime, toRemind, date);
                break;
            case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
                event = new MonthlyEvent(title, dateTime, toRemind, date);
                break;
            case RecurringEvent.YEARLY_RECURRENCE_TYPE:
                event = new YearlyEvent(title, dateTime, toRemind, date);
                break;
            default:
                throw new SystemException(ExceptionType.EXCEPTION_WRONG_RECURRING_TYPE);
            }
        } else {
            event = new Event(title, dateTime, toRemind, false);
        }
        return new AddEventCommand(event);
    }

    /**
     * Used for input of note content and processing the input into a readable data.
     *
     * @return A string of converted content input.
     * @throws StringIndexOutOfBoundsException if an error occurs.
     */
    public String inputContent() throws StringIndexOutOfBoundsException {
        Scanner input = new Scanner(System.in);
        StringBuilder commandInput = new StringBuilder();

        // Type note
        do {
            commandInput.append(input.nextLine());

            // Add next line when user press enter
            if (!commandInput.toString().equals(PREFIX_END)) {
                commandInput.append(STRING_NEW_LINE);
            }

            // "/del" Delete previous line if there user makes mistakes
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
     * @return Result of the delete note command.
     * @throws SystemException if an error occurs.
     */
    private Command prepareDeleteNote(String userMessage) throws SystemException {
        int index = NULL_INDEX;
        String title = "";
        String prefix = "";

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_INDEX:
                    exception = ExceptionType.EXCEPTION_MISSING_INDEX;
                    index = Integer.parseInt(checkBlank(infoDetails[1], exception));
                    break;
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    title = checkBlank(infoDetails[1], exception);
                    break;
                default:
                }
            }

            if (prefix.equalsIgnoreCase(PREFIX_TITLE)) {
                return new DeleteNoteCommand(title);
            } else {
                if (index <= NULL_INDEX) {
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                }
                return new DeleteNoteCommand(index);
            }
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
        userMessage = checkBlank(userMessage, ExceptionType.EXCEPTION_MISSING_KEYWORD);
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

    private Command prepareListEvent(String userMessage) {
        return new ListEventCommand();
    }

    /**
     * Parses the variables in userMessage to a form that is used in DeleteEventCommand.
     * @param userMessage User Input without the action word.
     * @return Returns a DeleteEventCommand to be executed by Duke.
     * @throws SystemException When the index is not numeric (e.g. index = 1%s).
     */
    private Command prepareDeleteEvent(String userMessage) throws SystemException {
        int index;
        try {
            index = Integer.parseInt(checkBlank(userMessage, SystemException.ExceptionType.EXCEPTION_MISSING_INDEX));
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }

        if (index <= NULL_INDEX) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
        }

        // Convert from human-readable index to index in array.
        return new DeleteEventCommand(index - 1);
    }
    /*
    private Command prepareViewNote(String userMessage) {
       return new ViewNoteCommand();
    }

    private Command prepareEditNote(String userMessage) {
       return new EditCommand(index, note);
    }

    private Command prepareEditEvent(String userMessage) {
       return new EditCommand(index, event);
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
                String prefix = infoDetails[0].toLowerCase();
                switch (prefix) {
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_INDEX:
                    ExceptionType exception = ExceptionType.EXCEPTION_MISSING_INDEX;
                    index = Integer.parseInt(checkBlank(infoDetails[1].trim(), exception));
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_WRONG_PREFIX);
                }
            }

            if (tags.isEmpty()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
            }

            if (index <= NULL_INDEX) {
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
        return new TagCommand(index - 1, tags);
    }

    /*
    private Command prepareRemind(String userMessage) {
       return new RemindCommand(index, isToRemind);
    }
    */
}
