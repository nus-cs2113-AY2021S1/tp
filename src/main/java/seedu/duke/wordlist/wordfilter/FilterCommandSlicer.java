package seedu.duke.wordlist.wordfilter;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.constants.Tags;
import seedu.duke.exceptions.wordexceptions.FilterCommandException;
import seedu.duke.exceptions.wordexceptions.FilterEmptyStringTagException;
import seedu.duke.exceptions.wordexceptions.FilterMissingTargetStringsTagException;
import seedu.duke.exceptions.wordexceptions.FilterMissingTargetWordTypeException;
import seedu.duke.exceptions.wordexceptions.FilterWordsInvalidWordType;

import java.util.ArrayList;

/**
 * This class is used to split the commands into different partitions that can be processed individually.
 */
public class FilterCommandSlicer {

    /**
     * Determines whether the program should continue on the previous filter.
     *
     * @param command Filter command from user's input.
     * @return True if the user wants to continue with the last filtered list.
     * @throws FilterCommandException When the command is not a filter command.
     */
    public static boolean isNewFilter(String command) throws FilterCommandException {
        if (!command.startsWith("filter words")) {
            throw new FilterCommandException();
        }

        return !command.toLowerCase().startsWith("filter words -continue");
    }

    /**
     * Gets the maximum number of words that the user wants to print after filtering.
     * This parameter is optional.
     *
     * @param command Filter command from user's input.
     * @return An integer referring to the maximum number of words.
     */
    public static int getWordPrintLimitFromFilterCommand(String command) {
        if (command.toLowerCase().contains(Tags.LIMIT_WORD)) {
            int limitIndex = command.indexOf(Tags.LIMIT_WORD);
            String cutCommand = command.substring(limitIndex + 7).trim();
            int nextSpaceIndex = cutCommand.indexOf(" ");
            String limitNumber;
            if (nextSpaceIndex != -1) {
                limitNumber = cutCommand.substring(0, nextSpaceIndex).trim();
            } else {
                // limit\ tag is at the end of the line
                limitNumber = cutCommand.trim();
            }
            try {
                return Integer.parseInt(limitNumber);
            } catch (NumberFormatException e) {
                System.out.println(FluffleMessages.INVALID_PRINT_LIMIT_MESSAGE);
            }
        }

        // if the print limit is not specified
        return -1;
    }

    /**
     * Gets the maximum number of words that the user wants to print from his list filter command.
     *
     * @param command "list filter" command from user's input.
     * @return Number of words that the user wants to print from the filtered list.
     */
    public static int getWordPrintLimitFromListFilterCommand(String command) {
        if (command.toLowerCase().contains(Tags.LIMIT_WORD)) {
            int limitIndex = command.indexOf(Tags.LIMIT_WORD);
            String cutCommand = command.substring(limitIndex + 7).trim();
            try {
                return Integer.parseInt(cutCommand);
            } catch (NumberFormatException e) {
                System.out.println(FluffleMessages.INVALID_PRINT_LIMIT_MESSAGE);
            }
        }
        // if the print limit is not specified
        return -1;
    }

    /**
     * Gets the word types need filtering.
     * Available word types are noun, verb and adjective.
     *
     * @param command Contains the word types need filtering.
     * @return Array of strings referring to word types.
     * @throws FilterWordsInvalidWordType When the user enters anything different from -noun, -verb, -adjective
     * @throws FilterMissingTargetWordTypeException When the user didn't enter any combination of word types
     */
    public static String[] getTargetedWordTypes(String command)
            throws FilterWordsInvalidWordType, FilterMissingTargetWordTypeException {
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> availableWordTypes = new ArrayList<>();
        initializeAvailableWordTypes(availableWordTypes);
        int index = command.indexOf(Tags.DASH);

        while (index >= 0) {
            int nextIndex = command.indexOf(Tags.DASH, index + 1);
            String wordType;

            if (nextIndex != -1) {
                wordType = command.substring(index + 1, nextIndex).trim();
            } else {
                wordType = command.substring(index + 1).trim();
            }

            if (wordType.startsWith("continue")) {
                index = command.indexOf(Tags.DASH, index + 1);
                continue;
            }

            if (availableWordTypes.contains(wordType)) {
                types.add(wordType);
            } else {
                throw new FilterWordsInvalidWordType();
            }
            index = command.indexOf(Tags.DASH, index + 1);
        }

        if (types.size() == 0) {
            throw new FilterMissingTargetWordTypeException();
        }

        return types.toArray(new String[0]);
    }

    /**
     * Gets string tags needs filtering in the word list.
     *
     * @param command String that contains the strings need filtering.
     * @return Array of strings referring to the strings need filtering.
     * @throws FilterMissingTargetStringsTagException When no string tag is found
     *                                                or when the command format is incorrect.
     * @throws FilterEmptyStringTagException When string tag with length 0 is provided.
     */
    public static String[] getTargetedStringTags(String command)
            throws FilterMissingTargetStringsTagException, FilterEmptyStringTagException {
        ArrayList<String> targetedStrings = new ArrayList<>();
        int index = command.indexOf(Tags.DASH);

        while (index >= 0) {
            int nextIndex = command.indexOf(Tags.DASH, index + 1);
            String stringToAdd;

            if (nextIndex != -1) {
                stringToAdd = command.substring(index + 1, nextIndex).trim();
            } else {
                stringToAdd = command.substring(index + 1).trim();
            }

            if (stringToAdd.length() != 0) {
                targetedStrings.add(stringToAdd);
            } else {
                throw new FilterEmptyStringTagException();
            }

            index = command.indexOf(Tags.DASH, index + 1);
        }

        if (targetedStrings.size() == 0) {
            throw new FilterMissingTargetStringsTagException();
        }

        return targetedStrings.toArray(new String[0]);
    }

    private static void initializeAvailableWordTypes(ArrayList<String> availableWordTypes) {
        availableWordTypes.clear();
        availableWordTypes.add(Tags.NOUN);
        availableWordTypes.add(Tags.VERB);
        availableWordTypes.add(Tags.ADJECTIVE);
    }

}
