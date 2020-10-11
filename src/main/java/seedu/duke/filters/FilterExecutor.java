package seedu.duke.filters;

import seedu.duke.exceptions.FilterCommandException;

import static seedu.duke.filters.FilterCommandSlicer.continueLastFilter;
import static seedu.duke.filters.FilterCommandSlicer.getTargetedStringTags;
import static seedu.duke.filters.FilterCommandSlicer.getTargetedWordType;
import static seedu.duke.filters.FilterCommandSlicer.getTypeOfFilter;
import static seedu.duke.filters.WordsFilter.*;

public class FilterExecutor {

    public static void executeFilterCommand(String command) {
        try {
            FilterType filterType = getTypeOfFilter(command);
            String[] tags;
            boolean isNewFilter = continueLastFilter(command);
            switch (filterType) {
            case WORD_TYPE:
                tags = getTargetedWordType(command);
                filterByType(isNewFilter, tags);
                break;
            case STARTING_STRING:
                tags = getTargetedStringTags(command);
                filterByStartingString(isNewFilter, tags);
                break;
            case CONTAINING_STRING:
                tags = getTargetedStringTags(command);
                filterByIncludedString(isNewFilter, tags);
                break;
            default:
                System.out.println("Filter type was not recognized in the command");
            }
        } catch (FilterCommandException e) {
            System.out.println("Filter command error");
        }
    }

}
