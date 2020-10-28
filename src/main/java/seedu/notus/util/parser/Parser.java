package seedu.notus.util.parser;

import seedu.notus.command.Command;

import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_END;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.notus.util.PrefixSyntax.STRING_SPLIT_DELIMITER;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Parses user input.
 */
public abstract class Parser {
    protected static final int CONTAINS_TAG_COLOR_INFO = 2;
    protected static final int NULL_INDEX = 0;
    protected String userMessage;
    protected static final String[] INSTRUCTIONS_INPUT_CONTENTS = {
        "Enter Note:", "*/del to delete previous line*", "*/end on a new line to end note input*"
    };

    //@@author Chongjx
    public Parser(String userMessage) {
        this.userMessage = userMessage;
    }

    public abstract Command parse() throws SystemException;

    /**
     * Splits the userMessage into the respective info by the delimiter.
     *
     * @param userMessage Original string of the user message.
     * @return Split strings.
     * @throws NullPointerException when the userMessage is empty.
     */
    static ArrayList<String[]> splitInfoDetails(String userMessage) throws SystemException {
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
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND);
        }
    }

    //@@author brandonywl
    /**
     * Checks if an input string if blank. If it is, throw the provided system exception. If it is not, return that
     * string trimmed.
     *
     * @param input Input to be checked.
     * @param exceptionType ExceptionType to be thrown.
     * @return Trimmed non-blank string.
     * @throws SystemException Occurs when input is blank.
     */
    static String checkBlank(String input, SystemException.ExceptionType exceptionType) throws SystemException {
        if (input.isBlank()) {
            throw new SystemException(exceptionType);
        } else {
            return input.trim();
        }
    }

    //@@author Chongjx
    /**
     * Creates and returns a Tag object based on the info provided.
     *
     * @param tagMessage info of the Tag. Contains tag name and may contain tag color.
     * @return new Tag object.
     * @throws SystemException for missing tag name.
     */
    static Tag handleTagPrefix(String[] tagMessage) throws SystemException {
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
        String[] tagInfo = tagsInfo.split(STRING_SPLIT_DELIMITER, 2);

        tagName = checkBlank(tagInfo[0], ExceptionType.EXCEPTION_MISSING_TAG);

        if (tagInfo.length == CONTAINS_TAG_COLOR_INFO) {
            tagColor = tagInfo[1].trim();
        }
        return new Tag(tagName, tagColor);
    }

    //@@author Nazryl
    /**
     * Used for input of note content and processing the input into a readable data.
     *
     * @return A string of converted content input.
     * @throws StringIndexOutOfBoundsException if an error occurs.
     */
    public static ArrayList<String> inputContent() {
        boolean isInputSuccess = false;
        ArrayList<String> inputString;

        do {
            Scanner input = new Scanner(System.in);
            inputString = new ArrayList<>();

            System.out.println(Formatter.formatString(INSTRUCTIONS_INPUT_CONTENTS, true));
            try {
                // Type note
                do {
                    inputString.add(input.nextLine());

                    // "/del" Delete previous line if there user makes a typo
                    if (inputString.get(inputString.size() - 1)
                            .equalsIgnoreCase(PREFIX_DELIMITER + PREFIX_DELETE_LINE)) {
                        inputString.remove(inputString.size() - 1);
                        inputString.remove(inputString.size() - 1);
                    }
                } while (!inputString.get(inputString.size() - 1)
                        .equalsIgnoreCase(PREFIX_DELIMITER + PREFIX_END)); // "/end" to end input note

                // Delete "/end" command when user ends the input
                inputString.remove(inputString.size() - 1);

                if (inputString.size() != 0) {
                    isInputSuccess = true;
                } else {
                    System.out.println(SystemException.ExceptionType.EXCEPTION_CONTENT_MISSING);
                }
            } catch (StringIndexOutOfBoundsException exception) {
                System.out.println(SystemException.ExceptionType.EXCEPTION_INVALID_END_INPUT);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println(SystemException.ExceptionType.EXCEPTION_INVALID_DEL_INPUT);
            }
        } while (!isInputSuccess);

        return inputString;
    }

    /**
     * Reverse the map integers values for an easier flow of
     * setting map.
     *
     * @param map Original string user inputs.
     * @return Result of the add note command.
     */
    public static Map<Integer, String> sortByKey(Map<Integer, String> map) {
        Map<Integer, String> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());
        reverseSortedMap.putAll(map);
        return reverseSortedMap;
    }

    /**
     * Adds on to the current type of lines that is to be added
     * edit or delete.
     *
     * @param prefix type that is passed in.
     * @param lists of list to be returned.
     * @param infoDetail string user input values.
     * @return lists of list containing the key and value.
     * @throws SystemException if integer is invalid.
     */
    public static Map<Integer, String> addToLists(String prefix, Map<Integer, String> lists,
                                            String infoDetail) throws SystemException {
        String line = checkBlank(infoDetail, ExceptionType.EXCEPTION_MISSING_LINE_CONTENT);
        String[] lineInfo;
        int index;

        if (prefix.equals(PREFIX_DELETE_LINE)) {
            lineInfo = line.split(STRING_SPLIT_DELIMITER, 1);
        } else {
            lineInfo = line.split(STRING_SPLIT_DELIMITER, 2);
        }

        index = Integer.parseInt(checkBlank(lineInfo[0], ExceptionType.EXCEPTION_MISSING_INDEX));
        if (index <= NULL_INDEX) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
        }

        if (prefix.equals(PREFIX_DELETE_LINE)) {
            lists.put(index - 1, "");
        } else {
            lists.put(index - 1, lineInfo[1]);
        }

        return lists;
    }

    //@@author R-Ramana
    /**
     * Set Up method for logging
     * Takes in a Logger variable to ensure that separates loggers can be used
     * for the respective methods.
     * Sets up what to be printed to the console (only logs that are severe)
     * Sets up what to be printed to the file (logs that are of Level.INFO and above)
     *
     * @param logger A Logger variable to be used
     * @param logFileName Name to be given for .log file.
     */
    void setupLogger(Logger logger, String logFileName) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler(logFileName);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException error) {
            logger.log(Level.SEVERE, "File logger not working.", error);
        }
    }
}