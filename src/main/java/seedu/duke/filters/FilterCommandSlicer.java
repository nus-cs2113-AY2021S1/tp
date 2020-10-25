package seedu.duke.filters;

import seedu.duke.constants.FilterMessages;
import seedu.duke.constants.Tags;
import seedu.duke.exceptions.FilterCommandException;

import java.util.ArrayList;

public class FilterCommandSlicer {

    /**
     * Determines whether the program should continue on the previous filter.
     *
     * @param command Filter command from user's input.
     * @return True if the user wants to continue with the last filtered list.
     * @throws FilterCommandException When the command is not a filter command.
     */
    public static boolean isNewFilter(String command) throws FilterCommandException {
        if (!command.startsWith("filter")) {
            throw new FilterCommandException();
        }

        return !command.toLowerCase().startsWith("filter -continue");
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
                System.out.println(FilterMessages.INVALID_PRINT_LIMIT_MESSAGE);
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
                System.out.println(FilterMessages.INVALID_PRINT_LIMIT_MESSAGE);
            }
        }
        // if the print limit is not specified
        return -1;
    }

    /**
     * Gets the word types need filtering (noun, verb, adjective).
     * Invalid word type will not be recognized for this version.
     *
     * @param command Contains the word types need filtering.
     * @return Array of strings referring to word types.
     * @throws FilterCommandException When no word type is found in the command.
     */
    public static String[] getTargetedWordTypes(String command) throws FilterCommandException {
        ArrayList<String> types = new ArrayList<>();
        if (command.toLowerCase().contains(Tags.NOUN_TAG)) {
            types.add(Tags.NOUN);
        }
        if (command.toLowerCase().contains(Tags.VERB_TAG)) {
            types.add(Tags.VERB);
        }
        if (command.toLowerCase().contains(Tags.ADJECTIVE_TAG)) {
            types.add(Tags.ADJECTIVE);
        }

        if (types.size() == 0) {
            throw new FilterCommandException();
        }
        return types.toArray(new String[0]);
    }

    /**
     * Gets string tags needs filtering in the word list.
     *
     * @param command String that contains the strings need filtering.
     * @return Array of strings referring to the strings need filtering.
     * @throws FilterCommandException When no string tag is found or when the command format is incorrect.
     */
    public static String[] getTargetedStringTags(String command) throws FilterCommandException {
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
                throw new FilterCommandException();
            }
            index = command.indexOf(Tags.DASH, index + 1);
        }

        if (targetedStrings.size() == 0) {
            throw new FilterCommandException();
        }
        return targetedStrings.toArray(new String[0]);
    }

}
