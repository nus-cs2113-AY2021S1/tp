package seedu.duke.wordlist.wordfilter;

import seedu.duke.constants.Tags;
import seedu.duke.exceptions.FilterCommandException;
import seedu.duke.exceptions.FilterTypeTagMissingException;

/**
 * An enumeration defining types of filter.
 */
public enum FilterType {
    WORD_TYPE,
    STARTING_STRING,
    INCLUDING_STRING;

    /**
     * Gets type of filter depending on the command entered.
     *
     * @param command String contains filter type.
     * @return Type of filter found in the command.
     * @throws FilterCommandException When filter type is not specified in the command.
     */
    public static FilterType getTypeOfFilter(String command)
            throws FilterCommandException, FilterTypeTagMissingException {
        if (!command.toLowerCase().contains(Tags.TYPE_OF_FILTER_TAG)) {
            throw new FilterTypeTagMissingException();
        }

        int indexOfTypeTag = command.indexOf(Tags.TYPE_OF_FILTER_TAG);
        String cutCommand = command.substring(indexOfTypeTag);
        cutCommand = cutCommand.replace(Tags.TYPE_OF_FILTER_TAG, Tags.BLANK);
        FilterType filterType;
        if (cutCommand.toLowerCase().trim().startsWith(Tags.TYPE_TAG)) {
            filterType = FilterType.WORD_TYPE;
        } else if (cutCommand.toLowerCase().trim().startsWith(Tags.START_TAG)) {
            filterType = FilterType.STARTING_STRING;
        } else if (cutCommand.toLowerCase().trim().startsWith(Tags.INCLUDE_TAG)) {
            filterType = FilterType.INCLUDING_STRING;
        } else {
            throw new FilterCommandException();
        }

        return filterType;
    }

}
