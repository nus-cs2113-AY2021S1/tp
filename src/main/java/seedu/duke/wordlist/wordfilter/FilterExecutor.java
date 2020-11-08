package seedu.duke.wordlist.wordfilter;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.exceptions.wordexceptions.FilterCommandException;
import seedu.duke.exceptions.wordexceptions.FilterMissingTargetStringsTagException;
import seedu.duke.exceptions.wordexceptions.FilterMissingTargetWordTypeException;
import seedu.duke.exceptions.wordexceptions.FilterEmptyStringTagException;
import seedu.duke.exceptions.wordexceptions.FilterTypeTagMissingException;
import seedu.duke.exceptions.wordexceptions.FilterWordsInvalidWordType;


import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.wordlist.wordfilter.FilterCommandSlicer.getWordPrintLimitFromFilterCommand;
import static seedu.duke.wordlist.wordfilter.FilterCommandSlicer.isNewFilter;
import static seedu.duke.wordlist.wordfilter.FilterCommandSlicer.getTargetedWordTypes;
import static seedu.duke.wordlist.wordfilter.FilterCommandSlicer.getTargetedStringTags;
import static seedu.duke.wordlist.wordfilter.FilterList.printFilterList;
import static seedu.duke.wordlist.wordfilter.FilterType.getTypeOfFilter;
import static seedu.duke.wordlist.wordfilter.WordsFilter.filterByType;
import static seedu.duke.wordlist.wordfilter.WordsFilter.filterByStartingString;
import static seedu.duke.wordlist.wordfilter.WordsFilter.filterByIncludedString;

/**
 * A class to process filter commands.
 * Sample command (must be in order): filter -continue by\start limit\10 -cs -cg
 */
public class FilterExecutor {

    private static final Logger LOGGER = Logger.getLogger("FilterExecutor");

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
        } catch (FilterTypeTagMissingException e) {
            LOGGER.log(Level.WARNING, "Filter type tag missing, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.FILTER_TYPE_TAG_MISSING);
        } catch (FilterCommandException e) {
            LOGGER.log(Level.WARNING, "Unknown filter command, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.FILTER_UNKNOWN_TYPE);
        } catch (FilterMissingTargetWordTypeException e) {
            LOGGER.log(Level.WARNING, "Empty target string, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.FILTER_WORDS_EMPTY_TARGET);
        } catch (FilterWordsInvalidWordType filterWordsInvalidWordType) {
            LOGGER.log(Level.WARNING, "Invalid word type provided, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.AVAILABLE_WORD_TYPE);
        } catch (FilterMissingTargetStringsTagException e) {
            LOGGER.log(Level.WARNING, "No string tag is provided, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.PROVIDE_STRING_TAG);
        } catch (FilterEmptyStringTagException e) {
            LOGGER.log(Level.WARNING, "Empty string tag is provided, catch block in executeFilterCommand");
            System.out.println(FluffleMessages.EMPTY_STRING_TAG);
        }
    }

}
