package seedu.duke.util;

import seedu.duke.command.*;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;
import static seedu.duke.util.PrefixSyntax.PREFIX_END;

/**
 * Parses user input.
 */
public class Parser {

    private static final int CONTAINS_TAG_COLOR_INFO = 2;

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

    private ArrayList<String[]> splitInfoDetails(String userMessage) throws NullPointerException {
        String[] splitMessage = userMessage.split("/");
        ArrayList<String[]> splitMessageContent = new ArrayList<>();

        // Splits the prefix and the remaining content
        for (String s : splitMessage) {
            splitMessageContent.add(s.split(" ", 2));
        }

        // Remove the first element as it is always empty
        splitMessageContent.remove(0);
        return splitMessageContent;
    }

    private Command prepareAddNote(String userMessage) {
        Note note;
        String title = null;
        String content;
        Boolean isPinned = false;
        ArrayList<Tag> tags = new ArrayList<Tag>();

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

    // add-n t[C++ to Java]
    // add-n pin[true] t[C++ to Java] tag[CS2113]
    // add-n /t C++ to Java /pin true /tag CS2113, Computing

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
     }*/

     private Command prepareCreateTag(String userMessage) {
         String tagName = "";
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
         } catch (NullPointerException exception) {
             return new IncorrectCommand("Missing description!");
         } catch (SystemException exception) {
             return new IncorrectCommand(exception.getMessage());
         }
         return new CreateTagCommand(tagName, tagColor);
     }

     private Command prepareDeleteTag(String userMessage) {
         String tagName = "";

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
                     //CASE PREFIX_INDEX:
                 //break;
                 }
             }
         } catch (NullPointerException exception) {
             return new IncorrectCommand("Missing description!");
         } catch (SystemException exception) {
             return new IncorrectCommand(exception.getMessage());
         }
         return new TagCommand(index, tagName, tagColor);
     }

     /*private Command prepareRemind(String userMessage) {
        return new RemindCommand(index, isToRemind);
     }
     */
}
