package seedu.duke.filters;

import seedu.duke.constants.FilterMessages;
import seedu.duke.exceptions.FilterCommandException;

import static seedu.duke.filters.FilterCommandSlicer.*;
import static seedu.duke.filters.FilterType.getTypeOfFilter;
import static seedu.duke.filters.WordsFilter.filterByType;
import static seedu.duke.filters.WordsFilter.filterByStartingString;
import static seedu.duke.filters.WordsFilter.filterByIncludedString;

/**
 * A class to process filter commands.
 */
public class FilterExecutor {

    /**
     * Executes filter functions depending on filter commands.
     *
     * @param command String referring to the command the user enters.
     */
    public static void executeFilterCommand(String command) {
        try {
            FilterType filterType = getTypeOfFilter(command);
            String[] tags;
            boolean isNewFilter = isNewFilter(command);
            int printLimit = getWordPrintLimitFromFilterCommand(command);
            switch (filterType) {
            case WORD_TYPE:
                tags = getTargetedWordTypes(command);
                assert tags.length != 0;
                filterByType(isNewFilter, tags, printLimit);
                break;
            case STARTING_STRING:
                tags = getTargetedStringTags(command);
                assert tags.length != 0;
                filterByStartingString(isNewFilter, tags, printLimit);
                break;
            case INCLUDING_STRING:
                tags = getTargetedStringTags(command);
                assert tags.length != 0;
                filterByIncludedString(isNewFilter, tags, printLimit);
                break;
            default:
                System.out.println(FilterMessages.FILTER_UNKNOWN_TYPE);
            }
        } catch (FilterCommandException e) {
            System.out.println(FilterMessages.FILTER_UNKNOWN_COMMAND);
        }
    }

}
