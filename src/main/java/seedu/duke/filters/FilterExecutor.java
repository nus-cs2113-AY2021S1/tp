package seedu.duke.filters;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.exceptions.FilterCommandException;

import static seedu.duke.filters.FilterCommandSlicer.getWordPrintLimitFromFilterCommand;
import static seedu.duke.filters.FilterCommandSlicer.isNewFilter;
import static seedu.duke.filters.FilterCommandSlicer.getTargetedWordTypes;
import static seedu.duke.filters.FilterCommandSlicer.getTargetedStringTags;
import static seedu.duke.filters.FilterList.printFilterList;
import static seedu.duke.filters.FilterType.getTypeOfFilter;
import static seedu.duke.filters.WordsFilter.filterByType;
import static seedu.duke.filters.WordsFilter.filterByStartingString;
import static seedu.duke.filters.WordsFilter.filterByIncludedString;

/**
 * A class to process filter commands.
 * Sample command (must be in order): filter -continue by\start limit\10 -cs -cg
 */
public class FilterExecutor {

    /**
     * Executes filter functions depending on filter commands.
     *
     * @param command String referring to the command the user enters.
     */
    public static void executeFilterCommand(String command) throws FilterCommandException {
        try {
            FilterType filterType = getTypeOfFilter(command);
            String[] tags;
            boolean isNewFilter = isNewFilter(command);
            int printLimit = getWordPrintLimitFromFilterCommand(command);
            switch (filterType) {
            case WORD_TYPE:
                tags = getTargetedWordTypes(command);
                assert tags.length != 0;
                filterByType(isNewFilter, tags);
                break;
            case STARTING_STRING:
                tags = getTargetedStringTags(command);
                assert tags.length != 0;
                filterByStartingString(isNewFilter, tags);
                break;
            case INCLUDING_STRING:
                tags = getTargetedStringTags(command);
                assert tags.length != 0;
                filterByIncludedString(isNewFilter, tags);
                break;
            default:
                throw new FilterCommandException();
            }
            printFilterList(printLimit);
        } catch (FilterCommandException e) {
            System.out.println(FluffleMessages.FILTER_UNKNOWN_COMMAND);
        }
    }

}
