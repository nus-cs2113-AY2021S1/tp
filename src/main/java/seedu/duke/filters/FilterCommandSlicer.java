package seedu.duke.filters;

import seedu.duke.constants.Tags;
import seedu.duke.exceptions.FilterCommandException;

import java.util.ArrayList;

public class FilterCommandSlicer {

    public static boolean isNewFilter(String command) {
        return !command.toLowerCase().contains(" -continue");
    }

    /**
     * Gets the word types need filtering (noun, verb, adjective).
     * Invalid word type will not be recognized for this version.
     *
     * @param command Contains the word types need filtering.
     * @return Array of strings referring to word types.
     * @throws FilterCommandException When no word type is found in the command.
     */
    public static String[] getTargetedWordType(String command) throws FilterCommandException {
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
